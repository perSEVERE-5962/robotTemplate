package frc.robot.subsystems;

import frc.robot.subsystems.*;
import frc.robot.sensors.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Autonomous extends Subsystem{
    private RemoteHCSR04 uValue = new RemoteHCSR04();

    private boolean Step1_inProgress = false;
    private boolean Step1_Started = false;
    private boolean Step1_done = false;
    private boolean isStep1Done(){
        return Step1_done;
    }

    private boolean Step2_inProgress = false;
    private boolean Step2_Started = false;
    private boolean Step2_done = false;
    public boolean isStep2Done(){
        return Step2_done;
    }

    private boolean Step3_done = false;
    private boolean Step3_Started = false;
    public boolean isStep3Done(){
        return Step3_done;
    }

    private boolean Step4_done = false;
    private boolean Step4_inProgress = false;
    private boolean Step4_Started = false;
    public boolean isStep4Done(){
        return Step4_done;
    }

    private boolean Step5_done = false;
    private boolean Step5_Started = false;
    public boolean isStep5Done(){
        return Step5_done;
    }    

    private boolean Step6_inProgress = false;
    private boolean Step6_Started = false;    
    private boolean Step6_done = false;
    public boolean isStep6Done(){
        return Step6_done;
    }

    private boolean Step7_done = false;
    private boolean Step7_inProgress = false;
    private boolean Step7_Started = false;
    public boolean isStep7Done(){
        return Step7_done;
    }

    private boolean Step8_inProgress = false;
    private boolean Step8_Started = false;
    private boolean Step8_done = false;
    public boolean isStep8Done(){
        return Step8_done;
    }



    private double step2LeftTarget = goStraight(107);
    private double step2RightTarget = goStraight(107);
    private double step4LeftTarget = goStraight(162);
    private double step4RightTarget = goStraight(162);
    private double backupLeftTarget = goStraight(-30);
    private double backupRightTarget = goStraight(-30);

    
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
    //private static int i=0;

    final double diameter = 3.0;//7.5 test bot
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
        Robot.logger.putMessage("Left Closed Loop Error = " + RobotMap.robotLeftTalon.getClosedLoopError());
        Robot.logger.putMessage("Right Closed Loop Error = " + RobotMap.robotRightTalon.getClosedLoopError());
       
        if(Math.abs(RobotMap.robotLeftTalon.getClosedLoopError())<100 && 
                Math.abs(RobotMap.robotRightTalon.getClosedLoopError())<100){
            return true;
        }
        return false;
    }
    public boolean onPosition(){
        return true;
    }
    public void stopDrive(){
        Robot.logger.putMessage("Stopping the drive");
        RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
        RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
        // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
        // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
        // RobotMap.robotLeftTalon.configPeakOutputForward(Constants.kSpeed, Constants.kTimeoutMs);
        // RobotMap.robotRightTalon.configPeakOutputForward(Constants.kSpeed, Constants.kTimeoutMs);
        // RobotMap.robotLeftTalon.configPeakOutputReverse(-Constants.kSpeed, Constants.kTimeoutMs);
        // RobotMap.robotRightTalon.configPeakOutputReverse(-Constants.kSpeed, Constants.kTimeoutMs);

    }
    public double goStraight(double distance){
        return (distance/circumferance)*ticksPerRotation;
    }

    public void Step1(){
        if(Step1_Started == false ){
            // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
            // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
            
            // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            // SmartDashboard.putString("STEP1_inProgress", "GREG");
            Step1_Started = true;
        }
        else if (Step1_inProgress == false){
            RobotMap.robotLeftTalon.set(ControlMode.Position,goStraight(48));
            RobotMap.robotRightTalon.set(ControlMode.Position,goStraight(48));
            //Robot.logger.putString("STEP1_inProgress", "YES");
            Step1_inProgress = true;
        }
        else if(onTarget()&&Step1_inProgress == true && Step1_done == false){
            //Step1_inProgress = false;
            //SmartDashboard.putString("Step1 onTarget", "YES");
            Step1_done = true;
        } 
        
    }
    public void Step2(){
        if(Step2_Started == false){
            // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
            // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
            // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);

            //SmartDashboard.putString("Step 2" , "Started");

            step2LeftTarget = goStraight(107) + RobotMap.robotLeftTalon.getSelectedSensorPosition();
            step2RightTarget = goStraight(107)+ RobotMap.robotRightTalon.getSelectedSensorPosition();

            // RobotMap.robotLeftTalon.configPeakOutputForward(0.7, Constants.kTimeoutMs);
            // RobotMap.robotRightTalon.configPeakOutputForward(0.7, Constants.kTimeoutMs);
            // RobotMap.robotLeftTalon.configPeakOutputReverse(-0.7, Constants.kTimeoutMs);
            // RobotMap.robotRightTalon.configPeakOutputReverse(-0.7, Constants.kTimeoutMs);
            Step2_Started = true;
        }
        else if(Step2_Started == true && Step2_inProgress == false){
            RobotMap.robotLeftTalon.set(ControlMode.Position, step2LeftTarget);//107
            RobotMap.robotRightTalon.set(ControlMode.Position,step2RightTarget);
            Robot.logger.putMessage("Step 2 in Progess");

            Step2_inProgress = true;
        }

        else if(onTarget() && Step2_inProgress == true && Step2_done == false){
            SmartDashboard.putString("Step2 onTarget", "yes");
            Robot.logger.putMessage("Step 2 Done!!");

            //stopDrive();
            Step2_done = true;
        }
        else{}
    }

    public void Step3(){
        angle = Robot.gyro.getGyroAngle();
        if(Robot.getIsLeft() == true && angle <=-17.35){
            stopDrive();
            Robot.gyro.resetGyro();
            Robot.logger.putMessage("Step 3 Done!!");

            Step3_done = true;
        }
       else if(Robot.getIsRight() == true && angle>=17.35){  
            stopDrive();
            Robot.gyro.resetGyro();
            Robot.logger.putMessage("Step 3 Done!!");

            Step3_done = true;
       }
        else{
          if(Robot.getIsRight() == true){
            turnRight(0.25);
            Robot.logger.putMessage("Step 3 in Progress");

          }
          else if(Robot.getIsLeft() == true){
            Robot.logger.putMessage("Step 3 in Progeses");

            turnLeft(0.25);
          }
         //   turnRight(-0.4);//-0.25 actual bot
        }
    }
    public void Step4(){
        if(Step4_Started == false){
        RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
		    RobotMap.robotRightTalon.setSelectedSensorPosition(0);    
            RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
            RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
        //     SmartDashboard.putNumber("LEFT START 4", RobotMap.robotLeftTalon.getSelectedSensorPosition(0));
        //     SmartDashboard.putNumber("RIGHT START 4", RobotMap.robotRightTalon.getSelectedSensorPosition(0));
        //     SmartDashboard.putNumber("LEFT START2 4", RobotMap.robotLeftTalon.getSensorCollection().getPulseWidthPosition());
        //     SmartDashboard.putNumber("RIGHT START2 4",RobotMap.robotRightTalon.getSensorCollection().getPulseWidthPosition());
            step4LeftTarget = goStraight(162)+ RobotMap.robotLeftTalon.getSelectedSensorPosition();
            step4RightTarget = goStraight(162)+ RobotMap.robotRightTalon.getSelectedSensorPosition();

            // RobotMap.robotalon.configPeakOutputForward(1, Constants.kTimeoutMs);
            // RobotMap.robotRightTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
            // RobotMap.robotLeftTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
            // RobotMap.robotRightTalon.configPeakOutputReverse(-1, Constants.kTimeoutMs);LeftT
            
            Robot.logger.putMessage("Step 4 started");
            Step4_Started = true;
        }
        else if(Step4_inProgress == false && Step4_Started == true){
            RobotMap.robotLeftTalon.set(ControlMode.Position, step4LeftTarget);//161
            RobotMap.robotRightTalon.set(ControlMode.Position,step4RightTarget);
            Robot.logger.putMessage("Step 4 in Progess");
 
            Step4_inProgress = true;
        }
        else if(onTarget() && Step4_inProgress == true && Step4_done == false){
            //stopDrive();
            Robot.logger.putMessage("Step 4 Done!!");
            Step4_done = true;
        }
    }
    public void Step5(){
        angle = Robot.gyro.getGyroAngle();
        if(Robot.getIsLeft() == true && angle >=110){
            stopDrive();
            Robot.gyro.resetGyro();
            Robot.logger.putMessage("Step 5 Done!!");

            Step5_done = true;
        }
        else if(Robot.getIsRight() == true && angle<=-110){  
            stopDrive();
            Robot.gyro.resetGyro();
            Robot.logger.putMessage("Step 5 Done!!");

            Step5_done = true;
        }
        else{
          if(Robot.getIsRight() == true){
            turnLeft(0.25);
            Robot.logger.putMessage("Step 5 in Progress");

          }
          else if(Robot.getIsLeft() == true){
            Robot.logger.putMessage("Step 5 in Progeses");
            turnRight(0.25);
          }
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
            // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
            // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
            // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
            // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);  
            Step6_Started = true;           
        }
        else if(Step6_Started == true && Step6_inProgress == false){
            RobotMap.robotLeftTalon.set(ControlMode.PercentOutput , 0.5);
            RobotMap.robotRightTalon.set(ControlMode.PercentOutput , 0.5);
            Step6_inProgress = true;
        }
        else if(range <=15 && Step6_inProgress == true && Step6_done == false){
            stopDrive();
            Step6_done = true;            
        }        
    }

    public void placeHatch() {
        if (Step7_Started == false) {
            Robot.logger.putMessage("Starting Auto Step PlaceHatch");
            Step7_Started = true;
        } else if (Step7_Started == true && Step7_inProgress == false) {
            Robot.armMotor.moveToPlaceHatch();
            Step7_inProgress = true;
        } else if (Robot.armMotor.isOnTarget() && Step7_inProgress == true && Step7_done == false) {
            Robot.logger.putMessage("Auto Step PlaceHatch Finished - final position = " +  RobotMap.armTalon.getSelectedSensorPosition());
            // deploy the pneumatics
            Robot.solenoidSubsystem.deployHatch();
            Step7_done = true;
        } else {
        }
    }

    public void backup() {
        if (Step8_Started == false) {
            Robot.logger.putMessage("Starting Auto Step Backup");
            Robot.solenoidSubsystem.retractHatch();
            backupLeftTarget = goStraight(-30)+ RobotMap.robotLeftTalon.getSelectedSensorPosition();
            backupRightTarget = goStraight(-30)+ RobotMap.robotRightTalon.getSelectedSensorPosition();
            Step8_Started = true;
        } else if (Step8_Started == true && Step8_inProgress == false) {
            RobotMap.robotLeftTalon.set(ControlMode.Position, backupLeftTarget);
            RobotMap.robotRightTalon.set(ControlMode.Position, backupRightTarget);
            Step8_inProgress = true;
        } else if (onTarget() && Step8_inProgress == true && Step8_done == false) {
            Robot.logger.putMessage("Auto Step Backup Finished");
           Step8_done = true;
        } else {
        }
        
    }
  
    @Override
    protected void initDefaultCommand() {

    }
}
