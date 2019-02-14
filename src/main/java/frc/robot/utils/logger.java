
package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class logger{
    public void putNumber(String key, double value){
        SmartDashboard.putNumber(key, value);
    }

    public double getNumber(String key, double value){
       return SmartDashboard.getNumber(key, value);
    }

    public void putString(String key, String value){
        SmartDashboard.putString(key, value);
    }
    public void putBoolean(String key, Boolean value){
        SmartDashboard.putBoolean(key, value);
    }
}
