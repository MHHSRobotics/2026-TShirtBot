package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

import com.ctre.phoenix6.CANBus;

public final class Constants {

    // Enum for different run modes for the code
    public static enum Mode {
        /** Running on a real robot. */
        REAL,

        /** Running a physics simulator. */
        SIM,

        /** Replaying from a log file. */
        REPLAY
    }

    public static final Mode simMode =
            Mode.SIM; // Default simulation mode, should be SIM for regular simulation and REPLAY for replays.

    public static final Mode currentMode =
            RobotBase.isReal() ? Mode.REAL : simMode; // Current mode the robot program is in

    public static final double loopTime = 0.02; // Period of main robot loop, 20ms default

    public static final CANBus defaultBus = new CANBus("rio"); // CAN bus used for non-swerve motors

    public static final double loopOverrunWarningTimeout =
            0.2; // Amount of time a robot tick can take before reporting a warning to DS

    public static final double brownoutVoltage = 6.0; // Voltage at which brownout protection occurs

    public static final double lowBatteryVoltage = 11.8; // Voltage at which low battery warning appears

    public static final double lowBatteryTime = 5; // How long to wait before reporting low battery

    public static final boolean ctreProLicensedWarning = true; // Whether to warn if a CTRE device isn't pro licensed

    // Toggles for susbsytems
    public static final boolean tankDriveEnabled = true;
    public static final boolean shooterEnabled = true;
    public static final boolean pitchAdjusterEnabled = true;
    public static final boolean turretEnabled = true;
    public static final boolean pneumaticsEnabled = true;
    public static final boolean ledsEnabled = false;
}
