package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.PitchAdjusterCommands;
import frc.robot.commands.PneumaticsCommands;
import frc.robot.commands.ShooterCommands;
import frc.robot.commands.TurretCommands;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.PitchAdjuster;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {
    // The controller. CommandPS5Controller should always be used rather than PS5Controller, since the former can be
    // bound to commands.
    private CommandPS5Controller controller = new CommandPS5Controller(0);

    // The subsystem fields
    private Drive drive;
    private PitchAdjuster pitchAdjuster;
    private Shooter shooter;
    private Turret turret;
    private Pneumatics pneumatics;

    // The subsystem command fields
    private DriveCommands driveCommands;
    private PitchAdjusterCommands pitchAdjusterCommands;
    private ShooterCommands shooterCommands;
    private TurretCommands turretCommands;
    private PneumaticsCommands pneumaticsCommands;

    public double speed = 0.55;

    public RobotContainer() {
        // Initialize the subsystems
        drive = new Drive();
        pitchAdjuster = new PitchAdjuster();
        shooter = new Shooter();
        turret = new Turret();
        pneumatics = new Pneumatics();

        // Initialize the subsystem commands
        driveCommands = new DriveCommands(drive);
        pitchAdjusterCommands = new PitchAdjusterCommands(pitchAdjuster);
        shooterCommands = new ShooterCommands(shooter);
        turretCommands = new TurretCommands(turret);
        pneumaticsCommands = new PneumaticsCommands(pneumatics);

        // Bind controls to commands
        configureBindings();
        SmartDashboard.setDefaultNumber("Speed", 0.5);
    }

    public void configureBindings() {
        drive.setDefaultCommand(driveCommands.drive(
                () -> -MathUtil.applyDeadband(controller.getLeftY(), 0.1),
                () -> -MathUtil.applyDeadband(controller.getLeftX(), 0.1)));
        pitchAdjuster.setDefaultCommand(
                pitchAdjusterCommands.setSpeed(() -> MathUtil.applyDeadband(controller.getRightY(), 0.1) / 10));
        // turret.setDefaultCommand(
        //        turretCommands.setSpeed(() -> MathUtil.applyDeadband(controller.getRightX(), 0.1) / 10));
        controller
                .R2()
                .onTrue(shooterCommands.setSpeed(() -> SmartDashboard.getNumber("Speed", 0.5)))
                .onFalse(shooterCommands.stop());

        controller.L2().onTrue(pneumaticsCommands.enable()).onFalse(pneumaticsCommands.disable());
    }

    // Gets the next auto command. Since this is t-shirt bot it just returns a no-op.
    public Command getAutonomousCommand() {
        return new InstantCommand();
    }
}
