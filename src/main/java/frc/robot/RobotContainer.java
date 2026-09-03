package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.LEDCommands;
import frc.robot.commands.PitchAdjusterCommands;
import frc.robot.commands.PneumaticsCommands;
import frc.robot.commands.ShooterCommands;
import frc.robot.commands.TurretCommands;
import frc.robot.io.EncoderIOCANcoder;
import frc.robot.io.MotorIOTalonFX;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.LEDS;
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
    private LEDS leds;

    // The subsystem command fields
    private DriveCommands driveCommands;
    private PitchAdjusterCommands pitchAdjusterCommands;
    private ShooterCommands shooterCommands;
    private TurretCommands turretCommands;
    private PneumaticsCommands pneumaticsCommands;
    private LEDCommands ledCommands;

    public double speed = 0.75;

    public RobotContainer() {
        // Initialize the subsystems

        if (Constants.tankDriveEnabled) {
            drive = new Drive();
            driveCommands = new DriveCommands(drive);
        }

        if (Constants.pitchAdjusterEnabled) {
            pitchAdjuster = new PitchAdjuster(
                    new MotorIOTalonFX(PitchAdjuster.Constants.motorId, "pitchAdjuster", "pitchAdjuster"),
                    new EncoderIOCANcoder(PitchAdjuster.Constants.encoderId, "pitchEncoder", "pitchAdjuster"));

            pitchAdjusterCommands = new PitchAdjusterCommands(pitchAdjuster);
        }

        if (Constants.shooterEnabled) {
            shooter = new Shooter(new MotorIOTalonFX(Shooter.Constants.motorId, "shooter", "shooter"));
            shooterCommands = new ShooterCommands(shooter);
        }

        if (Constants.turretEnabled) {
            turret = new Turret(new MotorIOTalonFX(Turret.Constants.motorId, "turret", "turret"));
            turretCommands = new TurretCommands(turret);
        }

        if (Constants.pneumaticsEnabled) {
            pneumatics = new Pneumatics();
            pneumaticsCommands = new PneumaticsCommands(pneumatics);
        }

        if (Constants.ledsEnabled) {
            leds = new LEDS(shooter);
            ledCommands = new LEDCommands(leds);
        }

        // Bind controls to commands
        configureBindings();
    }

    public void configureBindings() {
        if (Constants.tankDriveEnabled) {
            drive.setDefaultCommand(driveCommands.drive(
                    () -> -MathUtil.applyDeadband(controller.getLeftY(), 0.1) / 1.5,
                    () -> -MathUtil.applyDeadband(controller.getLeftX(), 0.1) / 1.5));
        }

        if (Constants.pitchAdjusterEnabled) {
            pitchAdjuster.setDefaultCommand(
                    pitchAdjusterCommands.setGoal(() -> MathUtil.applyDeadband(controller.getRightY(), 0.1)));
        }

        if (Constants.turretEnabled) {
            turret.setDefaultCommand(
                    turretCommands.setSpeed(() -> MathUtil.applyDeadband(controller.getRightX(), 0.1)));
        }
        if (Constants.shooterEnabled) {
            controller
                    .R2()
                    // .onTrue(shooterCommands.setSpeed(() -> SmartDashboard.getNumber("Speed", 0.5)))
                    .whileTrue(shooterCommands.setSpinning(true))
                    .onFalse(shooterCommands.setSpinning(false));
        }

        if (Constants.pneumaticsEnabled) {
            controller.L2().onTrue(pneumaticsCommands.enable()).onFalse(pneumaticsCommands.disable());
        }
    }

    // Gets the next auto command. Since this is t-shirt bot it just returns a no-op.
    public Command getAutonomousCommand() {
        return new InstantCommand();
    }

    public void periodic() {}
}
