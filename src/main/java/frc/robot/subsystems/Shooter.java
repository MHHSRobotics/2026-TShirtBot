package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

import frc.robot.io.MotorIO;

// The shooter subsystem uses a single TalonFX controlling a Falcon 500 motor, which powers a pair of flywheels. No PID
// is required since flywheels don't need to have a precise velocity.
public class Shooter extends SubsystemBase {
    public static class Constants {
        // ID of the TalonFX
        public static final int motorId = 4;
        public static final int baseMult = 80;

        // Whether the motor should be inverted
        public static final boolean inverted = false;
    }

    public boolean spinning = false;
    public LoggedNetworkNumber flywheelSpeed = new LoggedNetworkNumber("flywheels/speed", .65);

    // The motor controller
    private final MotorIO shooterMotor;

    public Shooter(MotorIO motor) {

        shooterMotor = motor;

        // Sets the inverted value for the config
        shooterMotor.setInverted(Constants.inverted);
    }

    // Sets the speed of the Falcon motor
    // public void setSpeed(double speed) {
    //     motor.set(speed);
    // }

    public double getSpeedFraction() {
        // 80 Is the max RPS for the motor
        return Math.abs(shooterMotor.getInputs().velocity / (Constants.baseMult * flywheelSpeed.get()));
    }

    public void setSpinning(boolean on) {
        spinning = on;
    }

    @Override
    public void periodic() {
        Logger.recordOutput("flywheels/currentSpeed", shooterMotor.getInputs().velocity);
        Logger.recordOutput("flywheels/targetSpeed", Constants.baseMult * flywheelSpeed.get());

        if (spinning) {
            shooterMotor.setDutyCycle(flywheelSpeed.get());
        } else {
            shooterMotor.setDutyCycle(0);
        }

        shooterMotor.update();
    }
}
