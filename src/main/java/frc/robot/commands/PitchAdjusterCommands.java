package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.PitchAdjuster;

public class PitchAdjusterCommands {
    private PitchAdjuster pitchAdjuster;

    public PitchAdjusterCommands(PitchAdjuster pitchAdjuster) {
        this.pitchAdjuster = pitchAdjuster;
    }

    // Returns a command that sets the speed of the pitch adjuster. Running the command sets the speed to whatever
    // speed.getAsDouble() returns.
    public Command setSpeed(DoubleSupplier speed) {
        return Commands.run(() -> pitchAdjuster.setSpeed(speed.getAsDouble()), pitchAdjuster);
    }

    // Returns a command that stops the pitch adjuster
    public Command stop() {
        return setSpeed(() -> 0);
    }
}
