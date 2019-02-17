/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.sensors.*;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Autonomous extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //public RobotGyro gyro = new RobotGyro();
  public double dist = 0;
  public double angle = 0;
  public boolean Step1_done = false;
  public boolean Step2_done = false;
  public boolean Step3_done = false;
  public boolean Step4_done = false;
  public boolean Step5_done = false;
  public boolean Step6_done = false;
  public boolean Step7_done = false;
  public boolean Step8_done = false;
  public boolean Step9_done = false;
  public boolean Step10_done = false;
  public void driveEncoder(double left, double right){
    RobotMap.myRobot.tankDrive(left , right);
  }
  public void stopDrive(){
    RobotMap.myRobot.tankDrive(0, 0);
  }
  public void turnLeft(double speed){
    RobotMap.myRobot.tankDrive(speed , -speed);
  }
  public void turnRight(double speed){
    RobotMap.myRobot.tankDrive(-speed , speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initialize() {
    Step1_done=false;
  }  

  public void Step1(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=48){
      Step1_done = true;
      Robot.magEncoder.reset();
    }
  }
  public void Step2(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=59){
      Step2_done = true;
      Robot.magEncoder.reset();
      stopDrive();
    }
    else{
      driveEncoder(-0.37892 , -0.37892);
    }
  }
  public void Step3(){
    angle = Robot.gyro.getGyroAngle();
    if(angle >=17.3532){//Should turn 67.29 degrees towards right
      //Robot.gyro.reset();
      Step3_done = true;      
      stopDrive();
    }
    else{
      if(Robot.getIsRight() == true){
        turnRight(0.5);
      }
      else if(Robot.getIsLeft() == true){
        turnLeft(0.5);
      }
    }
  }
  public void Step4(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=161.134){
      stopDrive();
      Step4_done = true;
      Robot.magEncoder.reset();
      
    }
    else{
      driveEncoder(0.7 , 0.7);
    }
  }
  public void Step5(){
    angle = Robot.gyro.getGyroAngle();
    if(angle >=-90){//Should turn 67.29 degrees towards left
      //Robot.gyro.reset();
      if(Robot.getIsRight() == true){
       turnLeft(0.5);
      }
      else if(Robot.getIsLeft() == true){
        turnRight(0.5);
      }
    }       
    else{
      Step5_done = true;
      stopDrive();
    }
  }
  public void Step6(){// Should it stop some distance before the cargo ship if yes, how much?
    dist = Robot.magEncoder.getDistance();
    if(dist>=48.06){
      Step6_done = true;
      Robot.magEncoder.reset();
      stopDrive();
    }
    else{
      driveEncoder(0.5 , 0.5);
    }
  }
  public void Step7(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=21.75){
      Step7_done = true;
      Robot.magEncoder.reset();
      stopDrive();
    }
    else{
      driveEncoder(0.3 , 0.3);
    }
  }

  private double getTurn(){    
    return 90;
  }
  private double getUltrasonicDist(){
    return 0;
  }

public void step8(){
  angle = Robot.gyro.getGyroAngle();
  if(angle >=5){
    Step8_done = true;      
    stopDrive();
  }
  else{
    if(Robot.getIsRight() == true){
      turnRight(5);
    }
    else if(Robot.getIsLeft() == true){
      turnLeft(5);
    }
  }
}
public void Step9(){
  dist = Robot.magEncoder.getDistance();
  if(dist>=118.75){
    Step9_done = true;
    Robot.magEncoder.reset();
    stopDrive();
  }
  else{
    driveEncoder(0.3 , 0.3);
    }
  }
public void step10(){
  angle = Robot.gyro.getGyroAngle();
  if(angle >=-5){
    Step10_done = true;      
    stopDrive();
  }
  else{
    if(Robot.getIsRight() == true){
      turnRight(-5);
    }
    else if(Robot.getIsLeft() == true){
      turnLeft(-5);
    }
  }
}
}


