package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Locale;

public class Logger {
    // File file;
    // BufferedWriter bufferedWriter;
    // FileWriter fileWriter;
    String newLine = System.lineSeparator();

    public Logger() {
        // try {
        //     double matchNumber = Robot.getMatchNumber();
        //     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
        //     // String logFilePath = "/u/robotlog_" + dateFormat.format(new Date()) + ".log";
        //     String logFilePath = "/u/robotlog_" + dateFormat.format(new Date()) + "_" + matchNumber + ".log";
        //     //String logFilePath = "/Users/dlemasurier/Team5962/git/LogTest/robotlog_" + dateFormat.format(new Date()) + ".log";
        //     fileWriter = new FileWriter(logFilePath);
        //     bufferedWriter = new BufferedWriter(fileWriter);
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd@HH-mm-ss", Locale.US);
        return dateFormat.format(new Date());
    }

    public void putNumber(String key, double value) {
        SmartDashboard.putNumber(key, value);
        writeKeyValue(key, value+"");
    }

    public void putString(String key, String value)  {
        SmartDashboard.putString(key, value);
        writeKeyValue(key, value);
    }

    public void putBoolean(String key, boolean value) {
        SmartDashboard.putBoolean(key, value);
        String valueString = "";
        try {
            valueString = Boolean.toString(value);
        } catch (NullPointerException npe) {
            valueString = "Unknown";
        } finally {
            writeKeyValue(key, valueString);
        }

    }
    
    public void putMessage(String message) {
        String logString = getCurrentTime() + " " + message + newLine;
        System.out.println(logString);
        // try {
        //     bufferedWriter.write(logString);
        //     bufferedWriter.flush();
        // } catch (Exception e) {
       	//     e.printStackTrace();
        //     System.out.println("putMessage: " + logString);
        // }
    }

    private void writeKeyValue(String key, String value) {
        putMessage(key + ", " + value);
    }

}

