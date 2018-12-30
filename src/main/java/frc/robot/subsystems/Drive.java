
package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	
	public Drive() {
	
	}

	public void joystickTank() {
		RobotMap.myRobot.tankDrive(Robot.oi.joystickLeftAxis(), Robot.oi.joystickRightAxis());
	}

	public void gameTank() {
		RobotMap.myRobot.tankDrive(Robot.oi.xBoxController.getRawAxis(1), Robot.oi.xBoxController.getRawAxis(5));
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
}