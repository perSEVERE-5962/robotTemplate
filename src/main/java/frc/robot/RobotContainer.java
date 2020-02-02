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
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoCommand;

import frc.robot.commands.DriveBackwards;
import frc.robot.commands.DriveForward;
import frc.robot.commands.DriveLeft;
import frc.robot.commands.DriveRight;

import frc.robot.commands.MoveArmDownCommand;
import frc.robot.commands.MoveArmUpCommand;

import frc.robot.commands.RunJamieDrive;
import frc.robot.commands.Climbstringup;
import frc.robot.commands.Hangup;
import frc.robot.commands.RunTankDrive;
import frc.robot.commands.SmoothArcadeDrive;
import frc.robot.commands.SmoothTankDrive;
import frc.robot.commands.StopDrive;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.MoveArm;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick driverController = new Joystick(0);
  private final Joystick copilotController = new Joystick(1);
  private final  JoystickButton buttonA = new JoystickButton(copilotController, 1);
  private final  JoystickButton buttonB = new JoystickButton(copilotController, 2);

  public double getIntake(){
    double ballIn = copilotController.getRawAxis(1);
    return ballIn;
  }

  public double getOuttake(){
    double ballOut = copilotController.getRawAxis(1);
    return ballOut;
  }

  public boolean armDown(){
    boolean ArmDown = copilotController.getRawButtonPressed(1);
    return ArmDown;
  }

  public boolean armUp(){
    boolean ArmUp = copilotController.getRawButtonPressed(2);
    return ArmUp;
  }

  private SendableChooser chooser= new SendableChooser<Command>();

  // The robot's subsystems and commands are defined here...
  private final Drive driveSubsystem = new Drive(driverController);
  private final AutoCommand autoCommand = new AutoCommand(driveSubsystem);
  private final MoveArm armSub = new MoveArm();
  // private final RunTankDrive driveCommand = new RunTankDrive(driveSubsystem);
  private Command driveCommand;
  private final JoystickButton buttonX = new JoystickButton(copilotController, 3);
  private final JoystickButton buttonY = new JoystickButton(copilotController, 4);

  private DriveLeft left = new DriveLeft(driveSubsystem);
  private DriveRight right= new DriveRight(driveSubsystem);
  private StopDrive stop = new StopDrive(driveSubsystem);
  private DriveForward goForward = new DriveForward(driveSubsystem);
  private DriveBackwards goBackwards = new DriveBackwards(driveSubsystem);

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
    buttonA.whenPressed(new MoveArmDownCommand());
    buttonB.whenPressed(new MoveArmUpCommand());
    buttonX.whenPressed(new Hangup());
    buttonY.whenPressed(new Climbstringup());

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