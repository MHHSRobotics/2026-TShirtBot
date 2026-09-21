package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.io.MotorIO;

// The turret subsystem uses a single TalonFX controlling a Falcon 500 motor.
public class Turret extends SubsystemBase {
    public static class Constants {
        // The ID of the TalonFX
        public static final int motorId = 5;
        public static final int encoderId = 2;

        // Whether the motor should be inverted
        public static final boolean motorInverted = true;
    }

    // The motor controller
    private MotorIO turretMotor;

    public Turret(MotorIO motor) {
        // Initialize the TalonFX
        turretMotor = motor;
        // Sets the inverted value for the config
        turretMotor.setInverted(Constants.motorInverted);
    }

    // Sets the speed of the Falcon motor
    public void setSpeed(double speed) {
        turretMotor.setDutyCycle(-speed);
    }

    @Override
    public void periodic() {
        turretMotor.update();
    }
}
