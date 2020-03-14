/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class UltrasonicHCSR04 {  
    private Ultrasonic topLeft = new Ultrasonic(0,1);
    private Ultrasonic topRight = new Ultrasonic(2,3);

    public UltrasonicHCSR04() {
        topLeft.setEnabled(true);
        topLeft.setAutomaticMode(true);
        topRight.setEnabled(true);
    }

    public double getTopLeftUltrasonicRange() {
        SmartDashboard.putNumber("Top Left Ultrasonic", topLeft.getRangeInches());
        return topLeft.getRangeInches();
    }

    public double getTopRightUltrasonicRange() {
        SmartDashboard.putNumber("Top Right Ultrasonic", topRight.getRangeInches());
        return topRight.getRangeInches();
    }

}
