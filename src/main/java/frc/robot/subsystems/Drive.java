package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// The drive subsystem uses a total of 4 PWM Spark motor controllers, 2 for each side of the differential drive. Each
// pair of motors controls three wheels.
public class Drive extends SubsystemBase {
    public static class Constants {
        // The IDs for each of the motor controllers
        public static final int leftMotor1Id = 0;
        public static final int leftMotor2Id = 1;
        public static final int rightMotor1Id = 2;
        public static final int rightMotor2Id = 3;

        // Whether each motor is inverted
        public static final boolean leftMotor1Inverted = false;
        public static final boolean leftMotor2Inverted = false;
        public static final boolean rightMotor1Inverted = true;
        public static final boolean rightMotor2Inverted = true;
    }

    // The motors
    private Spark leftMotor1;
    private Spark leftMotor2;
    private Spark rightMotor1;
    private Spark rightMotor2;

    // DifferentialDrive class to control the motors based on controller input
    private DifferentialDrive drive;

    public Drive() {
        // Initialize the motors
        leftMotor1 = new Spark(Constants.leftMotor1Id);
        leftMotor2 = new Spark(Constants.leftMotor2Id);
        rightMotor1 = new Spark(Constants.rightMotor1Id);
        rightMotor2 = new Spark(Constants.rightMotor2Id);

        // Invert them if necessary
        leftMotor1.setInverted(Constants.leftMotor1Inverted);
        leftMotor2.setInverted(Constants.leftMotor2Inverted);
        rightMotor1.setInverted(Constants.rightMotor1Inverted);
        rightMotor2.setInverted(Constants.rightMotor2Inverted);

        // Initialize the differential drive. This takes in two lambdas: one to set the speed of the left side, and one
        // for the right side. The DifferentialDrive class uses these to control the bot.
        drive = new DifferentialDrive(
                (s) -> {
                    leftMotor1.set(s);
                    leftMotor2.set(s);
                },
                (s) -> {
                    rightMotor1.set(s);
                    rightMotor2.set(s);
                });
    }

    // Sets the forward speed and rotation for the bot. drive.arcadeDrive converts these values into speeds for the left
    // and right sides.
    public void setSpeed(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }
}
