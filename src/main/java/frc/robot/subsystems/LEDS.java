// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Random;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDS extends SubsystemBase {

    public AddressableLED chargeUpLED;

    public AddressableLEDBuffer mainLEDBuffer;
    private Shooter shooter;

    public static int ledID = 4;

    public static int chargeLength = 131; // is 120 but added buffer incase miscalculation
    public static int topLength1 = 33;
    public static int topLength2 = 32;
    public static int bottomLength1 = 21;
    public static int bottomLength2 = 23;
    public static int indexCalcHolder;

    /** Creates a new LEDS. */
    public LEDS(Shooter shooterSubsystem) {

        this.shooter = shooterSubsystem;

        chargeUpLED = new AddressableLED(ledID);

        mainLEDBuffer = new AddressableLEDBuffer(chargeLength);

        chargeUpLED.setLength(mainLEDBuffer.getLength());

        chargeUpLED.start();
    }

    public void setChargeLeds(double amount) {

        // The equation held in indexCalcHolder is essentially the flipped index from i after the topLength
        // this was done in leu of how it was wired, which was back to front on one side,
        // then front to back on the other side and vise versa for the bottom row.
        if (amount > 0) {
            amount += new Random().nextDouble() / 10;
        }
        for (int i = 0; i < chargeLength; i++) {
            if (i <= topLength1) {
                indexCalcHolder = i;
            } else if (i <= topLength1 + topLength2) {
                indexCalcHolder = Math.abs(i - topLength1 - topLength2) + 1;
            } else if (i <= topLength1 + topLength2 + bottomLength1) {
                indexCalcHolder = i - topLength1 - topLength2;
            } else if (i <= topLength1 + topLength2 + bottomLength1 + bottomLength2) {
                indexCalcHolder = Math.abs(i - topLength1 - topLength2 - bottomLength1 - bottomLength2) + 1; // inverted
            }
            // establishes a gradient for the LEDs
            if (indexCalcHolder >= topLength1 * amount) {
                mainLEDBuffer.setRGB(i, 0, 0, 0);
            } else {
                mainLEDBuffer.setRGB(i, indexCalcHolder * 7, 255 - indexCalcHolder * 7, 0);
            }
        }
    }

    @Override
    public void periodic() {
        chargeUpLED.setData(mainLEDBuffer);
        // chargeUpLED2.setData(mainLEDBuffer);

        setChargeLeds(shooter.getSpeedFraction());
    }
}
