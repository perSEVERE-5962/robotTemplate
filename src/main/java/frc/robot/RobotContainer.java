/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoCommand;
// import frc.robot.commands.PathFollowing;
import frc.robot.commands.RunTankDrive;
import frc.robot.subsystems.Drive;
import frc.robot.commands.PathFollow;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.sensors.PIDControl;
import frc.robot.commands.MoveWinch;
import frc.robot.subsystems.Winch;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Joystick driverController = new Joystick(0);
  private final Joystick copilotController = new Joystick(1);
  // private final JoystickButton joyButton = new JoystickButton(copilotController, 5);
  // private final JoystickButton joyButton6 = new JoystickButton(copilotController, 6);

  // The robot's subsystems and commands are defined here...
  private final Drive driveSubsystem = new Drive();
  private final Winch winchSubsystem = new Winch();
  private final AutoCommand autoCommand = new AutoCommand(driveSubsystem);
  private final RunTankDrive driveCommand = new RunTankDrive(driveSubsystem);
  private final PIDControl pidControl = new PIDControl();
  private AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private final PathFollow followPath = new PathFollow(driveSubsystem, pidControl, ahrs);
  private final MoveWinch moveWinch = new MoveWinch(winchSubsystem);
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // copilotController.getBut
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
    return driveCommand;
  }

  public Joystick getDriverJoystick() {
    return driverController;
  }

  public Joystick getCopilotJoystick() {
    return copilotController;
  }
  public Command getFollowPath(){
    return followPath;
  }
  public Command getMoveWinch(){
    return moveWinch;
  }
  
}
