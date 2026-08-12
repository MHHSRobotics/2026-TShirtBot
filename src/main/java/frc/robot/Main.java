package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

// Do NOT edit this class. Any edits should be placed in Robot.java instead.
public final class Main {
    private Main() {}

    // This initializes and runs the Robot class
    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}
