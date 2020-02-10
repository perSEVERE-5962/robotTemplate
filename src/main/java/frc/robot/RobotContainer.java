/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.CameraLight;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


import frc.robot.commands.*;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick driverController = new Joystick(0);
  private final Joystick copilotController = new Joystick(1);

  // arm buttons
  private final  JoystickButton buttonA = new JoystickButton(copilotController, 1);
  private final  JoystickButton buttonB = new JoystickButton(copilotController, 2);
  private final  JoystickButton button8 = new JoystickButton(copilotController, 8);

  // camera led buttons
  private final JoystickButton buttonY = new JoystickButton(copilotController, 3);
  private final JoystickButton buttonX = new JoystickButton(copilotController, 4);
  



  // public double getIntake(){
  //   double axisValue = copilotController.getRawAxis(1);
  //   return axisValue;
  // }

  private SendableChooser chooser= new SendableChooser<Command>();

  // The robot's subsystems and commands are defined here...
  private final Drive driveSubsystem = new Drive(driverController);
  private final AutoCommand autoCommand = new AutoCommand(driveSubsystem);
  private final Arm armSub = new Arm();
  private final CameraLight cameraLight = new CameraLight();
  private final Winch winchSubsystem = new Winch();
  private final Elevator elevatorsubsystem = new Elevator();
  // private final RunTankDrive driveCommand = new RunTankDrive(driveSubsystem);

  private final WinchUp winchUp = new WinchUp(winchSubsystem);

  
  private final ColorSensor colorSensor = new ColorSensor();
  private final ControlPanel controlPanel = new ControlPanel(colorSensor);

  private final SenseColor senseColorCommand = new SenseColor(colorSensor);
  private final SpinToColor spinColorCommand = new SpinToColor(controlPanel);
  private final SpinRotations spinRotCommand = new SpinRotations(controlPanel);
  private Command driveCommand;
  private final RunIntake runIntake = new RunIntake();
  private final Shoot shoot = new Shoot();
  private final ElevatorUp elevatorUp = new ElevatorUp(elevatorsubsystem);
  private final ElevatorDown elevatorDown = new ElevatorDown(elevatorsubsystem);

    
  public Command getRunIntake(){
    return runIntake;
  }

  //private final CPSubsystem cpSubsystem = new CPSubsystem();

  private DriveLeft left = new DriveLeft(driveSubsystem);
  private DriveRight right= new DriveRight(driveSubsystem);
  private StopDrive stop = new StopDrive(driveSubsystem);
  private DriveForward goForward = new DriveForward(driveSubsystem);
  private DriveBackwards goBackwards = new DriveBackwards(driveSubsystem);
  private StopIntake stopIntake = new StopIntake(); 
  // private WinchUp winchUp = new WinchUp();

  public Command getShoot(){
    return shoot;
  }

  public Command getElevatorUp(){
    return elevatorUp;
  }

  public Command getElevatorDown(){
    return elevatorDown;
  }

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    chooser.setDefaultOption("smooth tankdrive", new SmoothTankDrive(driveSubsystem));
    chooser.addOption("smooth arcadedrive", new SmoothArcadeDrive(driveSubsystem));
    chooser.addOption("tankdrive", new RunTankDrive(driveSubsystem));
    chooser.addOption("arcadedrive", new ArcadeDrive(driveSubsystem));
    chooser.addOption("JamieDrive", new RunJamieDrive(driveSubsystem));
    SmartDashboard.putData("drivercontrol", chooser);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    buttonA.whenPressed(new MoveArmToIntake());
    buttonB.whenPressed(new MoveArmToShoot());
    buttonX.whenPressed(new TurnOnLight(cameraLight));
    buttonY.whenPressed(new TurnOffLight(cameraLight));
    button8.whenPressed(new ResetArm());
    // button7.whenPressed(new WinchUp());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }

  public Command getDriveCommand() {
    driveCommand=(Command)chooser.getSelected();
    return driveCommand;
  }

   public Command getTurnLeftCommand () {
     return left;
   }

  public Command getTurnRightCommand () {
    return right;
  }
  public Joystick getDriverJoystick() {
    return driverController;
  }

  public Joystick getCopilotJoystick() {
    return copilotController;
  }
  public Command getgoForward () {
    return goForward;
  }
  public Command getgoBackwards () {
    return goBackwards;
  }
  public Command getStopIntake(){
    return stopIntake;
  }

  public Command getWinchUp(){
    return winchUp;
  }


  public Command getSenseColorCommand(){
    return senseColorCommand;
  }

  public Command getSpinColorCommand(){
    return spinColorCommand;
  }

  public Command getSpinRotCommand(){
    return spinRotCommand;
  }
  
  
public Command stopdrive() {
  return stop;
}

public Command driveLeft() {
	return null;
}
public Command driveRight() {
	return null;
}

}