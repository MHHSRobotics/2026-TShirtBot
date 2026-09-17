package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import frc.robot.io.EncoderIO;
import frc.robot.io.MotorIO;

// THe pitch adjuster uses a single SparkMAX controlling a Neo motor. PID is not necessary here, since we don't have an
// encoder, and precise aiming is not needed for the t-shirt bot.
public class PitchAdjuster extends SubsystemBase {
    public static class Constants {
        // Motor ID
        public static final int motorId = 3;
        public static final int encoderId = 6;

        public static final double encoderOffset = -0.279184;
        public static final double gearRatio = 64;
        public static final double maxUp = Units.degreesToRadians(90);
        public static final double maxDown = Units.degreesToRadians(-10);

        public static final LoggedNetworkNumber kP = new LoggedNetworkNumber("pitch/kP", 20);
        public static final LoggedNetworkNumber kG = new LoggedNetworkNumber("pitch/kG", 0);
        public static final LoggedNetworkNumber kD = new LoggedNetworkNumber("pitch/kD", 5);
        public static final LoggedNetworkNumber kS = new LoggedNetworkNumber("pitch/KS", 4);
        public static final LoggedNetworkNumber fakeGoal = new LoggedNetworkNumber("pitch/psuedoGoal", 0);

        // Whether the motor is inverted
        public static final boolean inverted = false;
    }
    // SparkMax contoller for Neo
    private MotorIO pitchMotor;
    private EncoderIO pitchEncoder;

    public double goal = 0;

    public PitchAdjuster(MotorIO motor, EncoderIO encoder) {
        // Initialize the SparkMAX
        pitchMotor = motor;
        pitchEncoder = encoder;

        // Sets the inverted value of the config
        pitchMotor.setInverted(Constants.inverted);
        pitchMotor.connectEncoder(pitchEncoder, Constants.gearRatio);
        pitchMotor.setOffset(Constants.encoderOffset);
        // pitchMotor.setLimits(Constants.maxDown, Constants.maxUp);
    }

    // Sets the goal of the motor to the go
    public void setGoal(double go) {

        goal = go;
    }

    public void incrementGoal(double go) {

        goal = MathUtil.clamp(goal + go, Constants.maxDown, Constants.maxUp);
    }

    public void stop() {
        pitchMotor.brake();
    }

    @Override
    public void periodic() {

        Logger.recordOutput("pitch/encoderValue", pitchMotor.getInputs().position);
        Logger.recordOutput("pitch/setpointValue", pitchMotor.getInputs().setpoint);

        pitchMotor.setGoalWithCurrent(goal);

        pitchMotor.setkD(Constants.kD.get());
        pitchMotor.setkP(Constants.kP.get());
        pitchMotor.setkG(Constants.kG.get());
        pitchMotor.setkS(Constants.kS.get());

        pitchMotor.update();
        pitchEncoder.update();
    }
}
