package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

// THe pitch adjuster uses a single SparkMAX controlling a Neo motor. PID is not necessary here, since we don't have an
// encoder, and precise aiming is not needed for the t-shirt bot.
public class PitchAdjuster extends SubsystemBase {
    public static class Constants {
        // Motor ID
        private static final int id = 4;

        // Whether the motor is inverted
        private static final boolean inverted = false;
    }
    // SparkMax contoller for Neo
    private SparkMax motor;

    public PitchAdjuster() {
        // Initialize the SparkMAX
        motor = new SparkMax(Constants.id, MotorType.kBrushless);

        // config for the SparkMAX
        SparkMaxConfig config = new SparkMaxConfig();

        // Sets the inverted value of the config
        config.inverted(Constants.inverted);

        // Applies the config to the motor controller
        motor.configure(config, null, null);
    }

    // Sets the speed of the motor (1 is full forward, -1 is full reverse)
    public void setSpeed(double speed) {
        motor.set(speed);
    }
}
