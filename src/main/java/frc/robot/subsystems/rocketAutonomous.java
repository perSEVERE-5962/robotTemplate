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

  public double targetPos;
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
  final double diameter = 7.5;//7.5 test bot
  final double circumferance = Math.PI*diameter;
  final double ticksPerRotation = 4096;
  public double backupLeftTarget;
  public double backupRightTarget;
  public double goStraight(double distance){
    return (distance/circumferance)*ticksPerRotation;
}
  public boolean onTarget(){
    // Robot.logger.putMessage("Left Closed Loop Error = " + RobotMap.robotLeftTalon.getClosedLoopError());
    // Robot.logger.putMessage("Right Closed Loop Error = " + RobotMap.robotRightTalon.getClosedLoopError());
   
    if(Math.abs(RobotMap.robotLeftTalon.getClosedLoopError())<100 && 
            Math.abs(RobotMap.robotRightTalon.getClosedLoopError())<100){
        return true;
    }
    return false;
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void crossTheHabLine(){
    targetPos = (210/circumferance)*ticksPerRotation;//228.28

    if(crossingStarted == false){
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 30);
      crossingStarted = true;
    }
    else if(crossingStarted == true && crossingInProgress == false){
      RobotMap.robotLeftTalon.set(ControlMode.Position,40960);
      RobotMap.robotRightTalon.set(ControlMode.Position, 40960);
      crossingInProgress = true;
    }
    else if(onTarget() && crossingInProgress == true && isCrossed == false){
      Robot.logger.putMessage("Crossed the hab line");
      isCrossed = true;
    }

  } 
  public void turn90(){
    angle = Robot.gyro.getGyroAngle();
    if(Robot.getIsRight() == true && angle>= 90){
      Robot.logger.putMessage("Stopping the drive");
      RobotMap.robotLeftTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotRightTalon.set(ControlMode.PercentOutput, 0);
      RobotMap.robotLeftTalon.setSelectedSensorPosition(0);
      RobotMap.robotRightTalon.setSelectedSensorPosition(0);
      RobotMap.robotLeftTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      RobotMap.robotRightTalon.getSensorCollection().setPulseWidthPosition(0, 10);
      isTurned = true;
    }
    else if(Robot.getIsLeft() == true && angle<=-90){
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
  SmartDashboard.putNumber("uValue" ,  range);
  if(movingStarted == false){
    RobotMap.robotLeftTalon.set(ControlMode.PercentOutput , 0.5);
    RobotMap.robotRightTalon.set(ControlMode.PercentOutput , 0.5);
    movingStarted = true;
  }
  else if(movingStarted == true && isMoved == false && range<=20){
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
        backupLeftTarget = goStraight(-30)+ RobotMap.robotLeftTalon.getSelectedSensorPosition();
        backupRightTarget = goStraight(-30)+ RobotMap.robotRightTalon.getSelectedSensorPosition();
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
