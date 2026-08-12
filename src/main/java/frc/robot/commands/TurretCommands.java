package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Turret;

public class TurretCommands {
    private Turret turret;

    public TurretCommands(Turret turret) {
        this.turret = turret;
    }

    // Returns a command that sets the speed of the turret. Running the command sets the speed to whatever
    // speed.getAsDouble() returns.
    public Command setSpeed(DoubleSupplier speed) {
        return new InstantCommand(() -> turret.setSpeed(speed.getAsDouble()), turret);
    }

    // Returns a command to stop the turret
    public Command stop() {
        return setSpeed(() -> 0);
    }
}
