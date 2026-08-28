// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDS extends SubsystemBase {

    public AddressableLED chargeUpLED;

    public AddressableLEDBuffer mainLEDBuffer;
    private Shooter shooter;

    public static int chargeUp = 4;

    public static int chargeLength = 119; // is 120 but added buffer incase miscalculation
    public static int topLength = 36;
    public static int bottomLength = 24;
    public static int indexCalcHolder;

    /** Creates a new LEDS. */
    public LEDS(Shooter shooterSubsystem) {

        this.shooter = shooterSubsystem;

        chargeUpLED = new AddressableLED(chargeUp);

        mainLEDBuffer = new AddressableLEDBuffer(chargeLength);

        chargeUpLED.setLength(mainLEDBuffer.getLength());

        chargeUpLED.start();
    }

    public void setChargeLeds(double amount) {

        // The equation held in indexCalcHolder is essentially the flipped index from i after the topLength
        // this was done in leu of how it was wired, which was back to front on one side,
        // then front to back on the other side and vise versa for the bottom row.

        for (int i = 1; i < chargeLength; i++) {
            if (i <= topLength) {
                indexCalcHolder = i;
            } else if (i <= topLength * 2) {
                indexCalcHolder = Math.abs(i - topLength * 2); // inverted
                System.out.println(indexCalcHolder);
            } else if (i <= topLength * 2 + bottomLength) {
                indexCalcHolder = Math.abs(i - topLength * 2);
            } else if (i <= topLength * 2 + bottomLength * 2) {
                indexCalcHolder = Math.abs(i - topLength * 2 - bottomLength * 2); // inverted
            }

            if (indexCalcHolder > topLength * amount) {
                mainLEDBuffer.setRGB(i, 0, 0, 0);
            } else {
                if (indexCalcHolder < topLength / 3.) {
                    mainLEDBuffer.setRGB(i, 0, 255, 0);
                } else if (indexCalcHolder < topLength * 2. / 3) {
                    mainLEDBuffer.setRGB(i, 255, 255, 0);
                } else {
                    mainLEDBuffer.setRGB(i, 255, 0, 0);
                }
            }

            // mainLEDBuffer.setRGB(i, 255, 255, 255);
        }
    }

    @Override
    public void periodic() {
        chargeUpLED.setData(mainLEDBuffer);
        // chargeUpLED2.setData(mainLEDBuffer);

        setChargeLeds(shooter.getSpeedFraction());
    }
}
