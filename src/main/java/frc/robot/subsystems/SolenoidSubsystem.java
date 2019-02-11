package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SolenoidSubsystem extends Subsystem {
    // static Solenoid s0 = new Solenoid(0);
    // static Solenoid s1 = new Solenoid(1);
    // static Solenoid s2 = new Solenoid(2);
    // static Solenoid s3 = new Solenoid(3);
    final int SOL_TOP_R = 0;    //Blue
    final int SOL_BOT_R = 2;    //Orange
    final int SOL_TOP_L = 1;    //Green
    final int SOL_BOT_L = 3;    //Yellow

    DoubleSolenoid ds1 = new DoubleSolenoid(1, SOL_TOP_R, SOL_TOP_L);
    DoubleSolenoid ds2 = new DoubleSolenoid(1, SOL_BOT_R, SOL_BOT_L);


    public void initDefaultCommand() {

    }

    public void activateLeft() {
        // s0.set(true);
        // s1.set(false);
        // s2.set(true);
        // s3.set(false);
        ds1.set(DoubleSolenoid.Value.kReverse);
        ds2.set(DoubleSolenoid.Value.kReverse);
    }

    public void activateRight() {
        // s0.set(false);
        // s1.set(true);
        // s2.set(false);
        // s3.set(true);,1,1
        ds1.set(DoubleSolenoid.Value.kForward);
        ds2.set(DoubleSolenoid.Value.kForward);
   }

    public void stop() {
        // s0.set(false);
        // s2.set(false);
        // s1.set(false);
        // s3.set(false);

        ds1.set(DoubleSolenoid.Value.kOff);
        ds2.set(DoubleSolenoid.Value.kOff);
    }

    /*
DoubleSolenoid exampleDouble = new DoubleSolenoid(1, 2);

exampleDouble.set(DoubleSolenoid.Value.kOff);
exampleDouble.set(DoubleSolenoid.Value.kForward);
exampleDouble.set(DoubleSolenoid.Value.kReverse);
    */
}
