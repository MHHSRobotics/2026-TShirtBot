package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

// The turret subsystem uses a single TalonFX controlling a Falcon 500 motor.
public class Turret extends SubsystemBase {
    public static class Constants {
        // The ID of the TalonFX
        public static final int motorId = 6;

        // Whether the motor should be inverted
        public static final boolean motorInverted = true;
    }

    // The motor controller
    private TalonFX turretMotor;

    public Turret() {
        // Initialize the TalonFX
        turretMotor = new TalonFX(Constants.motorId);

        // Creates the TalonFX config
        TalonFXConfiguration config = new TalonFXConfiguration();

        // Sets the inverted value for the config
        config.MotorOutput.Inverted =
                Constants.motorInverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        // Applies the config to the TalonFX
        turretMotor.getConfigurator().apply(config);
    }

    // Sets the speed of the Falcon motor
    public void setSpeed(double speed) {
        turretMotor.set(speed);
    }
}
