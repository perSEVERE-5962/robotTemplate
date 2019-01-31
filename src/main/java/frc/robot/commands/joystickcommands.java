package org.usfirst.frc.team5962.robot.commands;

import org.usfirst.frc.team5962.robot.subsystems.BoxIntake;

import edu.wpi.first.wpilibj.command.Command;

public class RunBoxOutake extends Command{

	// Called just before this Command runs the first time
    protected void initialize() {

    	BoxIntake.boxStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	BoxIntake.boxOutake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	

    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
  }