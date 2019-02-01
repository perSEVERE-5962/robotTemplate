package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidSubsystem extends Subsystem {
static Solenoid s0 = new Solenoid(0);
static Solenoid s1 = new Solenoid(1);
static Solenoid s2 = new Solenoid(2);
static Solenoid s3 = new Solenoid(3);

public void initDefaultCommand(){

}

public void activateZero(){
    s1.set(false);
    s3.set(false);
    s0.set(true);
    s2.set(true);
}

public void activateOne(){
    s0.set(false);
    s2.set(false);
    s1.set(true);
    s3.set(true);
}

public void stop(){
    s0.set(false);
    s2.set(false);
    s1.set(false);
    s3.set(false);
}
}

