/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

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

public void Step1(){
  int dist = Robot.magEncoder.getDistance();
  if(dist>=48){
    Step1_done=true;
    encoder.reset ()
  }
}
public void initialize() {
  Step1-done=false;

public void Step2(){
  int dist = Robot.magEncoder.getDistance();
  if(dist>=49){
    Step1_done=true;
    encoder.reset ()
  }
}
public void initialize() {
  Step1-done=false;

public void Step3(){
  int dist = Robot.magEncoder.getDistance();
  if(dist>=179){
    Step1_done=true;
    encoder.reset ()
    }
  }
public void initialize() {
  Step1-done=false;

public void Step4(){
  int dist = Robot.magEncoder.getDistance();
  if(rotate 62.82 degrees to the left){
      Step1_done=true;
      encoder.reset ()
        }
      }
public void initialize() {
   Step1-done=false;

public void Step5(){
  int dist = Robot.magEncoder.getDistance();
  if(dist>=24){
    Step1_done=true;
    encoder.reset ()
    }
  }
 public void initialize() {
  Step1-done=false;