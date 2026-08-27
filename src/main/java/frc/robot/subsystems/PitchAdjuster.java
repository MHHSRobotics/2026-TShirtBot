package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

// THe pitch adjuster uses a single SparkMAX controlling a Neo motor. PID is not necessary here, since we don't have an
// encoder, and precise aiming is not needed for the t-shirt bot.
public class PitchAdjuster extends SubsystemBase {
    public static class Constants {
        // Motor ID
        private static final int id = 3;

        // Whether the motor is inverted
        private static final boolean inverted = false;
    }
    // SparkMax contoller for Neo
    private TalonFX motor;

    public PitchAdjuster() {
        // Initialize the SparkMAX
        motor = new TalonFX(Constants.id);

        // config for the SparkMAX
        TalonFXConfiguration config = new TalonFXConfiguration();

        // Sets the inverted value of the config
        config.MotorOutput.Inverted =
                Constants.inverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive;

        // Applies the config to the motor controller
        motor.getConfigurator().apply(config);
    }

    // Sets the speed of the motor (1 is full forward, -1 is full reverse)
    public void setSpeed(double speed) {
        motor.set(speed);
    }
}
