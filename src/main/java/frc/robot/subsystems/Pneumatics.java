package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    public static class Constants {
        public static final int id = 0;
    }

    private Solenoid solenoid;
    private Compressor compressor;

    public Pneumatics() {
        solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.id);
        compressor = new Compressor(1, PneumaticsModuleType.REVPH);
        compressor.enableDigital();
    }

    public void enable() {
        solenoid.set(true);
    }

    public void disable() {
        solenoid.set(false);
    }
}
