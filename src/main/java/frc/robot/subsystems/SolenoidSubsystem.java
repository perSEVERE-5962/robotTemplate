package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SolenoidSubsystem extends Subsystem {
    final int SOL_TOP_R = 0;    //Blue
    final int SOL_BOT_R = 2;    //Orange
    final int SOL_TOP_L = 1;    //Green
    final int SOL_BOT_L = 3;    //Yellow

    DoubleSolenoid ds1 = new DoubleSolenoid(1, SOL_TOP_R, SOL_TOP_L);
    DoubleSolenoid ds2 = new DoubleSolenoid(1, SOL_BOT_R, SOL_BOT_L);


    public void initDefaultCommand() {

    }

    public void activateLeft() {
        ds1.set(DoubleSolenoid.Value.kReverse);
        ds2.set(DoubleSolenoid.Value.kReverse);
    }

    public void activateRight() {
        ds1.set(DoubleSolenoid.Value.kForward);
        ds2.set(DoubleSolenoid.Value.kForward);
   }

    public void stop() {
        ds1.set(DoubleSolenoid.Value.kOff);
        ds2.set(DoubleSolenoid.Value.kOff);
    }

}
