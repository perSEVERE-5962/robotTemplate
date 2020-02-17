/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ElevatorUp;
import frc.robot.subsystems.Arm;

import frc.robot.sensors.PIDControl;
import frc.robot.subsystems.Drive;

import frc.robot.commands.PathFollow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command autonomousCommand;
  private Command driveCommand;
  private Command lightToggle;
  private Command spinColorCommand;
  private Command senseColorCommand;
  private Command spinRotCommand;
  private Command winchCommand;
  private Command armVision;
  private Command moveWinch;
  private Command cameraCommand;
  public PIDControl pidControl;
  public Drive drive;
  private RobotContainer m_robotContainer;
  private PathFollow autoPath;

  public Robot() {

    super(0.01);
  }

  private Command runIntake;
  private Command elevatorUp;
  private Command elevatorDown;
  private Arm arm = new Arm();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_robotContainer.resetGyro();

    cameraCommand = m_robotContainer.getCamera();
    cameraCommand.execute();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Gyro", m_robotContainer.getGyroAngle());
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
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_robotContainer.getDrive().resetEncoders();
    // autonomousCommand = m_robotContainer.getFollowPath();

    // if (autonomousCommand != null) {
    //   autonomousCommand.schedule();
    // }

    // if (autonomousCommand != null) {
    //   autonomousCommand.schedule();
    // }

    lightToggle = m_robotContainer.getTurnOnLight();
    if (lightToggle != null) {
      lightToggle.schedule();
    }
    
    armVision = m_robotContainer.getArmVision();
    if (armVision != null) {
      armVision.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  boolean arminshootposition = false;
  @Override
  public void autonomousPeriodic() {
    
    //if (m_robotContainer.getDrive().isPathFollowerDone()) {
      double ultrasonicLeft = m_robotContainer.getLeftUltrasonic();
      double ultrasonicRight = m_robotContainer.getRightUltrasonic();
      SmartDashboard.putNumber("LeftUltrasonic", ultrasonicLeft);
      SmartDashboard.putNumber("RightUltrasonic", ultrasonicRight);
      String action = m_robotContainer.getVisionAction();
      SmartDashboard.putString("Visionaction", action);

      m_robotContainer.putMessage("Left ultrasonic: " + ultrasonicLeft);
      m_robotContainer.putMessage("Right ultrasonic: " + ultrasonicRight);
      m_robotContainer.putMessage("Vision action: " + action);
      m_robotContainer.putMessage("Arm position: " + m_robotContainer.getArmPosition());

      if (ultrasonicLeft <= 9 && ultrasonicRight <=  9) {
        m_robotContainer.putMessage("Stop and shoot");
        
        stop();
         Command shoot = m_robotContainer.getShoot();
        if(shoot != null) {
          shoot.schedule();
        }
      } else if (action.equals("Left") && (ultrasonicLeft >= 54 && ultrasonicRight >=54)) {
        m_robotContainer.putMessage("Move left");
        moveLeft();
      } else if (action.equals("Right") && (ultrasonicLeft >= 54 && ultrasonicRight >=54)) {
        m_robotContainer.putMessage("Move right");
        moveRight();
      } else if (ultrasonicLeft >= 9 && ultrasonicRight >= 9) {
        m_robotContainer.putMessage("Inch forward");
        inchForward();
        if (arminshootposition == false&& (ultrasonicLeft < 54 && ultrasonicRight < 54)) {
          m_robotContainer.putMessage("Move arm to shoot position");
          m_robotContainer.moveArmToShoot();;
          arminshootposition = true;
        }
        
      } else {
        m_robotContainer.putMessage("Stop");
        // if (ultrasonicLeft <= 20 && ultrasonicRight <= 20) {
           stop();
        // } else {
        //   inchForward();
        // }

      }
    //}
  }
  

  private void moveLeft() {
    Command driveCommand = m_robotContainer.getTurnLeftCommand();
    if (driveCommand != null) {
      driveCommand.schedule();
    }
  }

  private void stop() {
    Command driveCommand = m_robotContainer.stopdrive();
    if (driveCommand != null) {
      driveCommand.schedule();
    }
  }

  private void moveRight() {
    Command driveCommand = m_robotContainer.getTurnRightCommand();
    if (driveCommand != null) {
      driveCommand.schedule();

    }
  }

  private void inchForward() {
    Command inchForwardCommand = m_robotContainer.getInchForward();
    if (inchForwardCommand != null) {
      inchForwardCommand.schedule();

    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    m_robotContainer.getDrive().resetEncoders();
    // winchCommand = m_robotContainer.getWinchUp();
    // if (winchCommand != null) {
    //   winchCommand.schedule();
    // }
    
    // motor = new BallCommands();
    // if (motor != null){
    // motor.schedule();
    // }

    driveCommand = m_robotContainer.getDriveCommand();
    if (driveCommand != null) {
      driveCommand.schedule();
    }

    lightToggle = m_robotContainer.getTurnOffLight();
    if (lightToggle != null) {
      lightToggle.schedule();
    }

    // senseColorCommand = m_robotContainer.getSenseColorCommand();
    // spinColorCommand = m_robotContainer.getSpinColorCommand();
    // spinRotCommand = m_robotContainer.getSpinRotCommand();

    // Drive.leftTalon().configNominalOutputForward(0.1, 30);
    // Drive.leftTalon().configNominalOutputReverse(-0.1, 30);
    // Drive.rightTalon().configNominalOutputForward(0.1, 30);
    // Drive.rightTalon().configNominalOutputReverse(-0.1, 30);

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    m_robotContainer.getDrive().writeEncoderValues();

    // spinRotCommand = m_robotContainer.getSpinRotCommand();
    // spinColorCommand = m_robotContainer.getSpinColorCommand();

    // if (!Constants.IS_SPIN_COMPLETE) {
    //   spinColorCommand.execute();
    // }

    SmartDashboard.putNumber("Arm Encoder Value", arm.getEncoderValues());
    if (m_robotContainer.getIntake() > 0.2) {
      runIntake = m_robotContainer.getRunIntake();
    } else if (m_robotContainer.getIntake() < -0.2) {
      runIntake = m_robotContainer.getShoot();
    } else {
      runIntake = m_robotContainer.getStopArm();
    }
    if (runIntake != null) {
      runIntake.schedule();

  }    if (m_robotContainer.getElevatorUpAxis()>0.2){
    elevatorUp = m_robotContainer.getElevatorUp();
    if (elevatorUp != null){
      elevatorUp.schedule();
    }
  }
    else if(m_robotContainer.getElevatorDownAxis()>0.2){
      elevatorDown = m_robotContainer.getElevatorDown();
      if(elevatorDown != null){
        elevatorDown.schedule();
      }
    }
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
