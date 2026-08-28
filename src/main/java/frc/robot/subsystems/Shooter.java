package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

// The shooter subsystem uses a single TalonFX controlling a Falcon 500 motor, which powers a pair of flywheels. No PID
// is required since flywheels don't need to have a precise velocity.
public class Shooter extends SubsystemBase {
    public static class Constants {
        // ID of the TalonFX
        public static final int motorId = 4;
        public static final int baseMult = 100;

        // Whether the motor should be inverted
        public static final boolean inverted = false;
    }

    public boolean spinning = false;
    public LoggedNetworkNumber flywheelSpeed = new LoggedNetworkNumber("flywheels/speed", .1);

    // The motor controller
    private final TalonFX motor;

    public Shooter() {
        // Initialize the TalonFX
        motor = new TalonFX(Constants.motorId);

        // Create config for the TalonFX
        TalonFXConfiguration config = new TalonFXConfiguration();

        // Sets the inverted value for the config
        config.MotorOutput.Inverted =
                Constants.inverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;

        // Applies the config to the TalonFX
        motor.getConfigurator().apply(config);
    }

    // Sets the speed of the Falcon motor
    // public void setSpeed(double speed) {
    //     motor.set(speed);
    // }

    public double getSpeedFraction() {
        // 80 Is the max RPS for the motor
        return Math.abs(motor.getVelocity().getValueAsDouble() / (Constants.baseMult * flywheelSpeed.get()));
    }

    public void setSpinning(boolean on) {
        spinning = on;
    }

    @Override
    public void periodic() {
        Logger.recordOutput("flywheels/currentSpeed", motor.getVelocity().getValueAsDouble());
        Logger.recordOutput("flywheels/targetSpeed", Constants.baseMult*flywheelSpeed.get());

        if (spinning) {
            motor.set(flywheelSpeed.get());
        } else {
            motor.set(0);
        }
        // System.out.println(Constants.flywheelSpeed.get());
    }
}
