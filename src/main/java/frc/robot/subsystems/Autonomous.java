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

/**
 * Add your docs here.
 */
public class Autonomous extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public RobotGyro gyro = new RobotGyro();
  public double dist;
  public boolean Step1_done;
  public boolean Step2_done;
  public boolean Step3_done;
  public boolean Step4_done;
  public boolean Step5_done;
  public boolean Step6_done;
  public boolean Step7_done;
  public boolean Step8_done;
  

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
    }
  }
  public void Step3(){
    if(gyro.getGyroAngle()>=getTurn()){//Should turn 67.29 degrees towards right
      gyro.resetGyro();
    }
  }
  public void Step4(){
    dist = Robot.magEncoder.getDistance();
    if(dist>=124.61){
      Step4_done = true;
      Robot.magEncoder.reset();
    }
  }
  public void Step5(){
    if(gyro.getGyroAngle()>=getTurn()){//Should turn 67.29 degrees towards left
      gyro.resetGyro();
    }       
  }
  public void Step6(){// Should it stop some distance before the cargo ship if yes, how much?
    dist = Robot.magEncoder.getDistance();
    if(dist>=48.06){
      Step6_done = true;
      Robot.magEncoder.reset();
    }
  }
  
  private double getTurn(){    
    return 90;
  }
  private double getUltrasonicDist(){
    return 0;
  }
}
public void initialize() {
  Step1-done=false;

