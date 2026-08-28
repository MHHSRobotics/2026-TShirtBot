package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.littletonrobotics.junction.Logger;

public class Pneumatics extends SubsystemBase {
    public static class Constants {
        public static final int id = 1;
        public static final int channlePort = 14;
        public static final int maxPressure = 120;
        public static final int minPressure = 95;
    }

    private Solenoid solenoid;
    private Compressor compressor;

    public Pneumatics() {
        solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.channlePort);
        compressor = new Compressor(1, PneumaticsModuleType.REVPH);

        compressor.enableAnalog(Constants.minPressure, Constants.maxPressure);

        // compressor.enableDigital();
    }

    public void enable() {

        solenoid.set(true);
    }

    public void disable() {
        solenoid.set(false);
    }

    @Override
    public void periodic() {
        Logger.recordOutput("pneumatics/pressure", compressor.getPressure());
        Logger.recordOutput("pneumatics/ready", (compressor.getPressure()/Constants.maxPressure)>=.9);
    }
}
