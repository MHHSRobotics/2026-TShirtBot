package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Pneumatics;

public class PneumaticsCommands {
    private Pneumatics pneumatics;

    public PneumaticsCommands(Pneumatics pneumatics) {
        this.pneumatics = pneumatics;
    }

    public Command enable() {
        return new InstantCommand(() -> pneumatics.enable(), pneumatics);
    }

    public Command disable() {
        return new InstantCommand(() -> pneumatics.disable(), pneumatics);
    }
}
