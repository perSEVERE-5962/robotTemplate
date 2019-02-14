package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.sensors.*;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class AutoPID{
    private static boolean Step1_done = false;

    private static int i=0;

    final double diameter = 7.5;
    final double circumferance = Math.PI*diameter;
    final double ticksPerRotation = 4096;
	private double ToInch(double units) {
		double deg = units * 360.0 / 4096.0;

		/* truncate to 0.1 res */
		deg *= 10;
		deg = (int) deg;
		deg /= 10;
		double inchConversion = (deg/360)*circumferance;
		
		return inchConversion;
    }
    private double getTicks(double inches) {
        return (inches*(4096.0/circumferance));
    }
    public void stop(){
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
         }

    public void step1(){
        double target = getTicks(36);
        Robot.LoggerUtils.putNumber("ticks",target);
        if(Step1_done == false){
            ++i;
            Robot.LoggerUtils.putNumber("step1 i", i);
            Step1_done = true;
        RobotMap.robotLeftTalon.set(ControlMode.Position, 1.5*ticksPerRotation);
        RobotMap.robotRightTalon.set(ControlMode.Position,1.5*ticksPerRotation);
      
        }
    }
    public boolean isStep1Done() {
        return Step1_done;
   }
}