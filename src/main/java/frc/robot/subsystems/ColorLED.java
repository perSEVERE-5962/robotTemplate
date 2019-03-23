package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

public class ColorLED extends Subsystem{

    Solenoid s4 = new Solenoid(1,4); //Green
    Solenoid s5 = new Solenoid(1,5); //Orange

    public void initDefaultCommand(){
        
    }

    public void switchOnGreen(){
        s5.set(false);
        s4.set(true);
    }

    public void switchOffGreen(){
        s4.set(false);
    }

    public void switchOnOrange(){
        s4.set(false);
        s5.set(true);
    }

    public void switchOffOrange(){
        s5.set(false);
    }
    
}
