/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import frc.robot.sensors.PIDControl;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.SPI;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoFiveSequence extends SequentialCommandGroup {
  /**
   * Creates a new ReplaceMeSequentialCommandGroup.
   */
  public AutoFiveSequence(RobotContainer robotC) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(robotC.getFollowPath(), robotC.getStraightPath(), robotC.getGyroTurn(), robotC.getEndPathFollower());
    // AHRS ahrs = new AHRS(SPI.Port.kMXP);
    // addCommands(new PathFollow(new Drive(), new PIDControl(), new AHRS(SPI.Port.kMXP)),
    //             new PathFollowStraight(new Drive(), new PIDControl(), new AHRS(SPI.Port.kMXP)),
    //             new GyroTurn180(new Drive(), new AHRS(SPI.Port.kMXP))
              

    /*
     * addCommands(
     *    robotC.getArmToIntake()
     *    robotC.getRunIntake(),
     *    robotC.getTurnOnLight(),
     *    robotC.getFollowPath(),
     *    robotC.getStraightPath(),
     *    robotC.getGyroTurn(),
     *    robotC.getEndPathFollower(),
     *    robotC.getArmVision());
     * new RunIntake(), robotC.getFollowPath(), robotC.getGyroTurn()
     * new RunIntake(), robotC.getFollowPath(), robotC.getStraightPath()
     * robotC.getGyroTurn(), 
     * robotC.getArmToIntake(),
     */    
  }
}
