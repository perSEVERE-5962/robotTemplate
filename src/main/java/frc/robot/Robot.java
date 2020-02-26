/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.sensors.PIDControl;
import frc.robot.subsystems.Drive;
import com.kauailabs.navx.frc.AHRS;


import frc.robot.commands.PathFollow;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public WPI_VictorSPX intake = new WPI_VictorSPX(19);

  private Command autonomousCommand;
  private Command driveCommand;
  private Command moveWinch;
  private Command turn;
  public PIDControl pidControl;
  public Drive drive;
  private RobotContainer m_robotContainer;
  private PathFollow autoPath;
  //private AHRS ahrs;
  private File file;
  private FileWriter fileWriter;
  private File fileN;
  private FileWriter fileWriterN;
  // private long start;
  // private long finish;
  // private long elapsed;
  public Robot(){
    
    super(0.01);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    // m_robotContainer.ahrs.reset();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */ 
  @Override
  public void autonomousInit() {
    intake.set(-1);

    autonomousCommand = m_robotContainer.getAutoSequence();
    // turn = m_robotContainer.getGyroTurn();
    // autonomousCommand.andThen(turn);
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }


    // if(turn != null){
    //   turn.schedule();
    // }
    // if(turn != null){
    //   turn.schedule();
    // }
    // try{
    // file = new File("/home/lvuser/accelerationLINEAR.csv");
    // fileWriter = new FileWriter(file, true);
    // fileWriter.write("accelX,  accelY, accelZ" + "\n");
    // fileWriter.close();
    // } catch(Exception e){   
    // }
    // try{
    //   fileN = new File("/home/lvuser/accelerationRAW.csv");
    //   fileWriterN = new FileWriter(file, true);
    //   fileWriterN.write("accelX,  accelY, accelZ" + "\n");
    //   fileWriterN.close();
    //   } catch(Exception e){   
    //   }
    // start = System.currentTimeMillis();

  }
 
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Angle NavX", m_robotContainer.ahrs.getAngle());
 
  //   Drive.robotLeftTalon.set(ControlMode.PercentOutput, 1);
  //   Drive.robotRightTalon.set(ControlMode.PercentOutput, 1);
  //   // SmartDashboard.putNumber("Right Velocity", Drive.robotRightTalon.getSelectedSensorVelocity());
  //   // SmartDashboard.putNumber("Left Velocity", Drive.robotLeftTalon.getSelectedSensorVelocity());
  //   // finish = System.currentTimeMillis();
  //   // elapsed = finish - start;
  //   try{    
  //     fileWriter = new FileWriter(file, true);
  //     fileWriter.write(m_robotContainer.ahrs.getWorldLinearAccelX() + ", " + m_robotContainer.ahrs.getWorldLinearAccelY() + ", " + m_robotContainer.ahrs.getWorldLinearAccelZ() + "\n" );
  //     fileWriter.close();
  //   }catch(Exception e){}
  //   try{    
  //     fileWriterN = new FileWriter(fileN, true);
  //     fileWriterN.write(m_robotContainer.ahrs.getRawAccelX() + ", " + m_robotContainer.ahrs.getRawAccelY() + ", " + m_robotContainer.ahrs.getRawAccelZ() + "\n" );
  //     fileWriterN.close();
  //   }catch(Exception e){}
  //   SmartDashboard.putNumber("Accelerationx", m_robotContainer.ahrs.getWorldLinearAccelX());
  //   SmartDashboard.putNumber("Accelerationy", m_robotContainer.ahrs.getWorldLinearAccelY());
  //   SmartDashboard.putNumber("Accelerationz", m_robotContainer.ahrs.getWorldLinearAccelZ());

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    Drive.robotRightTalon.setSelectedSensorPosition(0);
    Drive.robotLeftTalon.setSelectedSensorPosition(0);

    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    driveCommand = m_robotContainer.getDriveCommand();
    if (driveCommand != null) {
      driveCommand.schedule();
    }
    // moveWinch = m_robotContainer.getMoveWinch();
    // if(moveWinch != null){
    //   moveWinch.schedule();
    // }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("Encoder Right Value", Drive.rightTalon().getSelectedSensorPosition());
    SmartDashboard.putNumber("Encoder Left Value", Drive.leftTalon().getSelectedSensorPosition());
    SmartDashboard.putNumber("Angle NavX", m_robotContainer.ahrs.getAngle());

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }


}
