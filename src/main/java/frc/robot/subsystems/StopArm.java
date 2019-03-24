package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class StopArm extends Subsystem{

    final int SOL_TOP_R = 0; //Blue
    final int SOL_TOP_L = 1; //Green

    DoubleSolenoid ds1 = new DoubleSolenoid(1, SOL_TOP_R, SOL_TOP_L);

    public void initDefaultCommand(){
    
    }

    public void stopArmOn(){
        ds1.set(DoubleSolenoid.Value.kForward);
    }

    public void stopArmOff(){
        ds1.set(DoubleSolenoid.Value.kReverse);
    }
}