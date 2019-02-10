/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;


/**
 * Add your docs here.
 */
public class motionMagic extends Subsystem {
  final int kTimeoutMs = 10;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void pidControl(){
    /* first choose the sensor */
  RobotMap.robotRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,  kTimeoutMs);
  RobotMap.robotRightTalon.setSensorPhase(true);

 /* set the peak, nominal outputs, and deadband */
  RobotMap.robotRightTalon.configNominalOutputForward(0, kTimeoutMs);
  RobotMap.robotRightTalon.configNominalOutputReverse(0, kTimeoutMs);
  RobotMap.robotRightTalon.configPeakOutputForward(1, kTimeoutMs);
  RobotMap.robotRightTalon.configPeakOutputReverse(-1, kTimeoutMs);

 /* set closed loop gains in slot0 */
  //RobotMap.robotRightTalon.config_kF(kPIDLoopIdx, 0.34, kTimeoutMs);
  RobotMap.robotRightTalon.config_kP(0, 0.2, kTimeoutMs);
  RobotMap.robotRightTalon.config_kI(0, 0, kTimeoutMs);
  RobotMap.robotRightTalon.config_kD(0, 0, kTimeoutMs);

  }
}
