/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

/**
 * Add your docs here.
 */
public class UltrasonicHCSR04 {
    NetworkTable networkTable;
    public UltrasonicHCSR04(){
        NetworkTableInstance netTabInst = NetworkTableInstance.getDefault();
        networkTable = netTabInst.getTable("Ultrasonic HCSR04");
    }
    public double getLeftValue(){
        return(networkTable.getEntry("Left Value").getDouble(0));
    }
    public double getRightValue(){
        return(networkTable.getEntry("Right Value").getDouble(0));
    }
    public double averageRange(){
        return(getLeftValue()+getRightValue())/2;
    }
}
