package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.Drive;

public class DriveCommands {
    private Drive drive;

    public DriveCommands(Drive drive) {
        this.drive = drive;
    }

    // Returns a command that continuously sets the speed of the drive to the values returned by the speed and angle
    // DoubleSuppliers. This is not an InstantCommand, so it will run forever (or until manually stopped).
    public Command drive(DoubleSupplier speed, DoubleSupplier angle) {
        return Commands.run(() -> drive.setSpeed(speed.getAsDouble(), angle.getAsDouble()), drive);
    }
}
