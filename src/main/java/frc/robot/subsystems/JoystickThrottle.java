package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class JoystickThrottle extends Subsystem {

	//TODO: Recreate this so it can take any joystick so that it doesn't rely on oi and robotmap.myRobot
	public static void Speed() {
		//double throttle = (((Robot.oi.joystickRight.getThrottle() * -1) + 2) / 3);
		//SmartDashboard.putString("Throttle", "" + throttle);
		//RobotMap.myRobot.setMaxOutput(throttle);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}