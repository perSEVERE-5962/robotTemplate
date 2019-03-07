/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
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
public class RemoteHCSR04 {

    NetworkTable table;

    public RemoteHCSR04() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
	    table = inst.getTable("HC-SR04");
    }

    public double getLeftRange(){
        return (table.getEntry("Left Distance").getDouble(0));
    }

    public double getRightRange(){
        return (table.getEntry("Right Distance").getDouble(0));
    }
    public double getRange(){
        return (getLeftRange() + getRightRange())/2;
    }
}

