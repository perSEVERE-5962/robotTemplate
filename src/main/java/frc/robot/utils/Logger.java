
package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

public class Logger {
    BufferedWriter writer;

    public Logger() {
//         try {
//             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            writer = new BufferedWriter(new FileWriter("file" + timestamp + ".txt"));
//         } catch (IOException E) {
//         }
    }

    public void putNumber(String key, double value) {
        SmartDashboard.putNumber(key, value);
//         try {
//             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//             writer.append(key + ", " + value + " " + timestamp);
//         } catch (IOException ioe) {

//         }            
    }

    public double getNumber(String key, double value)  {
//         try {
//             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//             writer.append(key + ", " + value + " " + timestamp);
//         } catch (IOException ioe) {

//         } 
        return SmartDashboard.getNumber(key, value);
    }

    public void putString(String key, String value)  {
        SmartDashboard.putString(key, value);
//         try {
//             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//             writer.append(key + ", " + value + " " + timestamp);
//         } catch (IOException ioe) {

//         }
    }

    public void putBoolean(String key, Boolean value) {
        SmartDashboard.putBoolean(key, value);
//         try {
//             Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//             writer.append(key + ", " + value + " " + timestamp);
//         } catch (IOException ioe) {

//         }
    }

}
