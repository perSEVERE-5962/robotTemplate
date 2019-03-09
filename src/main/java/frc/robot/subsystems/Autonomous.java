package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.sensors.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Autonomous{
    public RemoteHCSR04 uValue = new RemoteHCSR04();
    private static boolean Step1_done = false;
    public boolean isStep1Done(){
        return Step1_done;
    }
    public static boolean Step1_inProgress = false;
    public static boolean Step1_Started = false;
    private static boolean Step2_done = false;
    public boolean isStep2Done(){
        return Step2_done;
    }
    public static boolean Step2_inProgress = false;
    public boolean Step2_Started = false;
    private static boolean Step3_done = false;
    public boolean isStep3Done(){
        return Step3_done;
    }
    public boolean Step3_Started = false;
    private static boolean Step4_done = false;
    public boolean isStep4Done(){
        return Step4_done;
    }
    public boolean Step4_inProgress = false;
    public boolean Step4_Started = false;
    private static boolean Step5_done = false;
    public boolean isStep5Done(){
        return Step5_done;
    }    
    public boolean Step5_Started = false;
    private static boolean Step6_done = false;
    public boolean isStep6Done(){
        return Step6_done;
    }
    public static boolean Step6_inProgress = false;
    public boolean Step6_Started = false;
    private static boolean Step7_done = false;
    public boolean isStep7Done(){
        return Step7_done;
    }
    public static boolean Step7_inProgress = false;
    public boolean Step7_Started = false;
    private static boolean Step9_done = false;//game piece placement

    
    public double angle=0;

    public void turnLeft(double speed){
        Robot.logger.putNumber("Turning Left", Robot.gyro.getGyroAngle());
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, -speed);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, speed);
      }
      public void turnRight(double speed){
        Robot.logger.putNumber("Turning Right", Robot.gyro.getGyroAngle());
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, speed);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, -speed);      
    }
    private static int i=0;

    final double diameter = 7.5;//3.0 actual bot
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
    public boolean onTarget(){
        if(Math.abs(RobotMap.robotLeftTalon.getClosedLoopError())<100 && Math.abs(RobotMap.robotRightTalon.getClosedLoopError())<100){
            return true;
        }
        return false;
    }
    public boolean onPosition(){
        return true;
    }
    public void stopDrive(){
        Robot.logger.putMessage("Stopping the drive");
        RobotMap.robotLeftTalon.set(ControlMode.Disabled, 0);
        RobotMap.robotRightTalon.set(ControlMode.Disabled, 0);
        RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
        RobotMap.robotRightTalon.setSelectedSensorPosition(0);
        RobotMap.robotLeftTalon.configPeakOutputForward(Constants.kSpeed, Constants.kTimeoutMs);
        RobotMap.robotRightTalon.configPeakOutputForward(Constants.kSpeed, Constants.kTimeoutMs);
        RobotMap.robotLeftTalon.configPeakOutputReverse(-Constants.kSpeed, Constants.kTimeoutMs);
        RobotMap.robotRightTalon.configPeakOutputReverse(-Constants.kSpeed, Constants.kTimeoutMs);

    }
    public double goStraight(double distance){
        return (distance/circumferance)*ticksPerRotation;
    }

    public void Step1(){
        if(Step1_Started == false ){
            // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
            // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
            
            RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            SmartDashboard.putString("STEP1_inProgress", "GREG");
            Step1_Started = true;
        }
        else if (Step1_inProgress == false){
            RobotMap.robotLeftTalon.set(ControlMode.Position,goStraight(48));
            RobotMap.robotRightTalon.set(ControlMode.Position,goStraight(48));
            SmartDashboard.putString("STEP1_inProgress", "YES");
            Step1_inProgress = true;
        }
        else if(onTarget()&&Step1_inProgress == true && Step1_done == false){
            //Step1_inProgress = false;
            SmartDashboard.putString("Step1 onTarget", "YES");
            Step1_done = true;
        } 
        
    }
    public void Step2(){
        if(Step2_Started == false){
            RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
            RobotMap.robotRightTalon.setSelectedSensorPosition(0);
            RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            Step2_Started = true;
        }
        else if(Step2_Started == true && Step2_inProgress == false){
            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(107));//107
            RobotMap.robotRightTalon.set(ControlMode.Position,goStraight(107));
            Step2_inProgress = true;
        }

        else if(onTarget()&& Step2_inProgress == true && Step2_done == false){
            SmartDashboard.putString("Step2 onTarget", "yes");
            //stopDrive();
            Step2_done = true;
        }
        else{}
    }
    // public void runSteps(){
    //     if(Step1_done == false){
    //         Step1();
    //     }
    //     else if(Step1_done == true){
    //         Step2();
    //     }
    // }
    public void Step3(){
        angle = Robot.gyro.getGyroAngle();
        if(Robot.getIsLeft() == true && angle <=-90){
            stopDrive();
            Step3_done = true;
        }
       else if(Robot.getIsRight() == true && angle>=90){  
            stopDrive();
            Step3_done = true;
       }
        else{
          if(Robot.getIsRight() == true){
            turnRight(0.25);
          }
          else if(Robot.getIsLeft() == true){
            turnLeft(0.25);
          }
         //   turnRight(-0.4);//-0.25 actual bot
        }
    }
    public void Step4(){
        if(Step4_Started == false){
            RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
		    RobotMap.robotRightTalon.setSelectedSensorPosition(0);    
            RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);

            Step4_Started = true;
        }
        else if(Step4_inProgress == false && Step4_Started == true){

            RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(15));//161
            RobotMap.robotRightTalon.set(ControlMode.Position,goStraight(15)); 
            Step4_inProgress = true;
        }
        else if(onTarget() && Step4_inProgress == true && Step4_done == false){
            //stopDrive();
            Step4_done = true;
        }
    }
    public void Step5(){
        angle = Robot.gyro.getGyroAngle();
        //Robot.getIsLeft() == true && 
        if(angle <=-180){
            stopDrive();
            Step5_done = true;
        }
    //    else if(Robot.getIsRight() == true && angle>=90){  
    //         stopDrive();
    //         Step5_done = true;
    //    }
   
        else{
            // if(Robot.getIsRight() == true){
            //    turnLeft(-0.25);
            // }
            // else if(Robot.getIsLeft() == true){
              turnLeft(0.25);
            // }
        }
    }
    // public void Step6(){
    //     if(Step6_Started == false){
    //         RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
    //         RobotMap.robotRightTalon.setSelectedSensorPosition(0);
    //         RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
    //         RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);    
    //         Step6_Started = true;
    //     }
    //     else if(Step6_Started == true && Step6_inProgress == false){
    //         RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(40));
    //         RobotMap.robotRightTalon.set(ControlMode.Position,goStraight(40)); 
    //         Step6_inProgress = true;
    //     }
    //     else if(onTarget() && Step6_inProgress == true && Step6_done == false){
    //         stopDrive();
    //         Step6_done = true;
    //     }
    // }
    // public void Step7(){
    //     if(Step7_Started == false){            
    //         RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
    //         RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);    
    //         Step7_Started  = true;
    //     }
    //     else if(Step7_Started == true && Step7_inProgress == false){
    //         RobotMap.robotLeftTalon.set(ControlMode.Position, goStraight(21.75));
    //         RobotMap.robotRightTalon.set(ControlMode.Position,goStraight(21.75)); 
    //         Step7_inProgress = true;
    //     }
    //     else if(onTarget() && Step7_inProgress == true && Step7_done == false){
    //         stopDrive();
    //         Step7_done = true;
    //     }
    // }
    public double range;
    public void driveToHatch(){
        range = uValue.getRange();
        if(Step6_Started == false){            
            RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
            RobotMap.robotRightTalon.setSelectedSensorPosition(0);
            RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);  
            Step6_Started = true;           
        }
        else if(Step6_Started == true && Step6_inProgress == false){
            goStraight(-0.5);
            Step6_inProgress = true;
        }
        else if(range <=10 && Step6_inProgress == true && Step6_done == false){
            stopDrive();
            Step6_done = true;            
        }        
    }
    public void placeHatch(){
    //step7
    }
    //
}
