package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

// Robot class initialized by Main. Extends TimedRobot to get access to methods which are called when state is changed.
public class Robot extends TimedRobot {
    // Current autonomous command
    private Command autonomousCommand;

    private final RobotContainer robotContainer;

    public Robot() {
        robotContainer = new RobotContainer();
    }

    // robotPeriodic() runs every robot tick. This code just runs the CommandScheduler, which manages all commands.
    // CommandScheduler.run() gets the next command and runs it.
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    // autonomousInit() is called when autonomous begins. This gets the auto command from RobotContainer and schedules
    // it. There's no auto for t-shirt bot, but I decided to keep this since it's a good example.
    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    // teleopInit() is called when teleop begins. Here it just cancels whatever auto command was running. Again, this is
    // useless for t-shirt bot.
    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }
}
