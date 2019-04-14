/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.subsystems.*;
import frc.robot.sensors.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class rocketAutonomous extends Subsystem {
  private RemoteHCSR04 uValue = new RemoteHCSR04();

  public double leftTargetPos;
  public double rightTargetPos;
  public double angle;
  public double range;
  public boolean crossingStarted = false;
  public boolean crossingInProgress = false;
  public boolean isCrossed = false;
  public boolean turningStarted = false;
  public boolean isTurned = false;
  public boolean movingStarted = false;
  public boolean movingInProgress = false;
  public boolean isMoved = false;
  public boolean placeHatch_started = false;
  public boolean placeHatch_inProgress = false;
  public boolean placeHatch_done = false;
  public boolean deployHatch = false;
  public boolean backup_started = false;
  public boolean backup_inProgress = false;
  public boolean backup_done = false;  
  public boolean goLittle_started = false;
  public boolean goLittle_inProgress = false;
  public boolean goLittle_done = false;
  final double diameter = 3.0;//7.5 test bot
  final double circumferance = Math.PI*diameter;
  final double ticksPerRotation = 4096;
  public double backupLeftTarget;
  public double backupRightTarget;
  
  public double goStraight(double distance){
    return (distance/circumferance)*ticksPerRotation;
}
  public boolean onTarget(){
    if(RobotMap.robotLeftTalon.getSelectedSensorPosition() >= (leftTargetPos-10) && RobotMap.robotRightTalon.getSelectedSensorPosition() >= (rightTargetPos-10)){
      SmartDashboard.putString("ONTARGET " , RobotMap.robotLeftTalon.getSelectedSensorPosition() + "");
      return true;
    }
    // Robot.logger.putMessage("Left Closed Loop Error = " + RobotMap.robotLeftTalon.getClosedLoopError());
    // Robot.logger.putMessage("Right Closed Loop Error = " + RobotMap.robotRightTalon.getClosedLoopError());
   
    // if(Math.abs(RobotMap.robotLeftTalon.getClosedLoopError())<10 && 
    //         Math.abs(RobotMap.robotRightTalon.getClosedLoopError())<10){
        //return true;
    // }
    SmartDashboard.putNumber("LEFTPOS" , RobotMap.robotLeftTalon.getSelectedSensorPosition());
    SmartDashboard.putNumber("RIGHTPOS" , RobotMap.robotRightTalon.getSelectedSensorPosition());
    SmartDashboard.putNumber("LEFT_TPOS" , leftTargetPos);
    SmartDashboard.putNumber("RIGHT_TPOS" , rightTargetPos);
    return false;
  
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // public void goLittleLterallyNothing(){
  //   // if(goLittle_started == true && goLittle_inProgress == false){
  //     RobotMap.robotLeftTalon.set(ControlMode.Position,500);
  //     RobotMap.robotRightTalon.set(ControlMode.Position, 500);
  //   //   goLittle_inProgress = true;
  //   // }
  //   // else if(onTarget() && goLittle_inProgress == true && goLittle_done == false){
  //   //   Robot.logger.putMessage("Crossed the hab line");
  //      goLittle_done = true;
  //   // }
  // }
  public void crossTheHabLine(){
    if(crossingStarted == false){
      leftTargetPos =  RobotMap.robotLeftTalon.getSelectedSensorPosition() +(49/circumferance)*ticksPerRotation;//228.28
      rightTargetPos =  RobotMap.robotRightTalon.getSelectedSensorPosition() +(49/circumferance)*ticksPerRotation;//228.28
      
      // RobotMap.robotLeftTalon.configPeakOutputForward(0.5, Constants.kTimeoutMs);
      // RobotMap.robotRightTalon.configPeakOutputForward(0.5, Constants.kTimeoutMs);
      // RobotMap.robotLeftTalon.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);
      // RobotMap.robotRightTalon.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);
      // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      crossingStarted = true;
    }
    else if(crossingStarted == true && crossingInProgress == false){
      RobotMap.robotLeftTalon.set(ControlMode.Position, leftTargetPos);
      RobotMap.robotRightTalon.set(ControlMode.Position, rightTargetPos);

      crossingInProgress = true;
    }
    else if(onTarget() && crossingInProgress == true && isCrossed == false){
      Robot.logger.putMessage("Crossed the hab line");
      Robot.logger.putNumber("Left Enc", RobotMap.robotLeftTalon.getSelectedSensorPosition());
      Robot.logger.putNumber("Right Enc", RobotMap.robotRightTalon.getSelectedSensorPosition());
      
      // RobotMap.robotLeftTalon.configPeakOutputForward(Constants.kSpeed, Constants.kTimeoutMs);
      // RobotMap.robotRightTalon.configPeakOutputForward(Constants.kSpeed, Constants.kTimeoutMs);
      // RobotMap.robotLeftTalon.configPeakOutputReverse(-Constants.kSpeed, Constants.kTimeoutMs);
      // RobotMap.robotRightTalon.configPeakOutputReverse(-Constants.kSpeed, Constants.kTimeoutMs);
      isCrossed = true;
    }
 

  } 
  public void turn37(){
    angle = Robot.gyro.getGyroAngle();
    if(Robot.getIsRight() == true && angle>= 31){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      isTurned = true;
    }
    else if(Robot.getIsLeft() == true && angle<=-31){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      isTurned = true;
    }
    else{
     if(Robot.getIsRight() == true){ 
      Robot.logger.putNumber("Turning Right", Robot.gyro.getGyroAngle());
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0.25);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, -0.25); 
      
    }
     else if(Robot.getIsLeft() == true){ 
      Robot.logger.putNumber("Turning Left", Robot.gyro.getGyroAngle());
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, -0.25);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0.25); 
      
    }
  }


  }  
  public void placeHatch() {
    SmartDashboard.putNumber("Arm Autonomous Value" , RobotMap.armTalon.getSelectedSensorPosition());
    if (placeHatch_started == false) {
        Robot.logger.putMessage("Starting Auto Step PlaceHatch");
        placeHatch_started = true;
    } else if (placeHatch_started == true && placeHatch_inProgress == false) {
        Robot.armMotor.moveToPlaceHatch();
        placeHatch_inProgress = true;
    } else if (Robot.armMotor.isOnTarget() && placeHatch_inProgress == true && placeHatch_done == false) {
        Robot.logger.putMessage("Auto Step PlaceHatch Finished - final position = " +  RobotMap.armTalon.getSelectedSensorPosition());
        // deploy the pneumatics
        placeHatch_done = true;
    } else {
    }
}
public void goToTheRocket(){
  range = uValue.getLeftRange();
  Robot.logger.putNumber("uValue" ,  range);
  if(movingStarted == false){
    RobotMap.robotLeftTalon.set(ControlMode.PercentOutput , 0.5);
    RobotMap.robotRightTalon.set(ControlMode.PercentOutput , 0.5);
    movingStarted = true;
  }
  else if(movingStarted == true && isMoved == false && range<=8){
    Robot.logger.putString("At the rocket" , "done");
    RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
    RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
    isMoved = true;
    // RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
    // RobotMap.robotRightTalon.setSelectedSensorPosition(0);
    // RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
    // RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
  }
}
public void deployHatch(){
  if(deployHatch == false){
    Robot.solenoidSubsystem.deployHatch(); 
    deployHatch = true;

  }
}
public void backup() {
    if (backup_started == false) {
        Robot.logger.putMessage("Starting Auto Step Backup");
        //Robot.solenoidSubsystem.retractHatch();
        backupLeftTarget = goStraight(-50)+ RobotMap.robotLeftTalon.getSelectedSensorPosition();
        backupRightTarget = goStraight(-50)+ RobotMap.robotRightTalon.getSelectedSensorPosition();
        backup_started = true;
    } else if (backup_started == true && backup_inProgress == false) {
        RobotMap.robotLeftTalon.set(ControlMode.Position, backupLeftTarget);
        RobotMap.robotRightTalon.set(ControlMode.Position, backupRightTarget);
        backup_inProgress = true;
    } else if (onTarget() && backup_inProgress == true && backup_done == false) {
        Robot.logger.putMessage("Auto Step Backup Finished");
       backup_done = true;
    } else {
    }
    
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
