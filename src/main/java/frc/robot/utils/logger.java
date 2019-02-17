
package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;



public class logger{ 
    BufferedWriter writer;
public logger(){ 
        try{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    writer = new BufferedWriter(new FileWriter("file"+timestamp+".txt"));}
    catch(IOException E){
    }
}
    public void putNumber(String key, double value)throws IOException{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        writer.append(key+", "+value +" "+timestamp);
        SmartDashboard.putNumber(key, value);
    }

    public double getNumber(String key, double value)throws IOException{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        writer.append(key+", "+value +" "+timestamp);
       return SmartDashboard.getNumber(key, value);
    }

    public void putString(String key, String value)throws IOException{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        writer.append(key+", "+value +" "+timestamp);
        SmartDashboard.putString(key, value);
    }
    public void putBoolean(String key, Boolean value)throws IOException{
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        writer.append(key+", "+value +" "+timestamp);
        SmartDashboard.putBoolean(key, value);
    }
   

}

