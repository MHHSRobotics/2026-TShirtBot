package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Shooter;

// Commands class that gives commands for the shooter subsystem
public class ShooterCommands {
    // The shooter object this commands object controls
    private Shooter shooter;

    public ShooterCommands(Shooter shooter) {
        // Initializes the shooter object
        this.shooter = shooter;
    }

    // Returns a command that sets the speed of the shooter. Running the command sets the speed to whatever
    // speed.getAsDouble() returns.
    public Command setSpeed(DoubleSupplier speed) {
        return new InstantCommand(() -> shooter.setSpeed(speed.getAsDouble()), shooter);
    }

    // Returns a command to stop the shooter
    public Command stop() {
        return setSpeed(() -> 0);
    }
}
