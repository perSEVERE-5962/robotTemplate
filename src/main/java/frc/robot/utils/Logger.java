
package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

public class Logger {
    File file;
    BufferedWriter bufferedWriter;
    FileWriter fileWriter;
    String newLine = System.lineSeparator();

    public Logger() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd@HH-mm-ss", Locale.US);
            String logFilePath = "/u/robotlog_" + dateFormat.format(new Date()) + ".log";
            file = new File(logFilePath);
            if(!f.exists()){
                f.createNewFile();
            }
            fw = new FileWriter(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);
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
        String valueString;
        try {
            valueString = Boolean.toString(value);
        } catch (NullPointerException npe) {
            valueString = "Unknown";
        } finally {
            writeKeyValue(key, valueString);
        }

    }
    
    public void putMessage(String message) {
        try {
            bufferedWriter.append(getCurrentTime() + " " + message + newLine);
        } catch (IOException ioe) {

        }
    }

    private void writeKeyValue(String key, String value) {
        putMessage(key + ", " + value);
    }

}
