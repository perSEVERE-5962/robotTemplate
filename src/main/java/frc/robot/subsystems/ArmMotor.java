// package frc.robot.subsystems;
// import edu.wpi.first.wpilibj.Victor;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import frc.robot.*;
// public class ArmMotor extends Subsystem {
    
//     // Put methods for controlling this subsystem
//     // here. Call these from Commands.
	
// WPI_TalonSRX armTalon = RobotMap.armTalon;
	
// final double START_POSITION = 0;                // [\]
// final double PLACE_HATCH_POSITION = 0.3 * 4096; // [|]
// final double SHOOT_BALL_POSITION = 0.7 * 4096;  // [/]
// final double INTAKE_BALL_POSITION = 0.75 * 4096;// [_]

// public void moveToPlaceHatch (){
// 	RobotMap.armTalon.set(ControlMode.Position, PLACE_HATCH_POSITION);
// }

// public void moveToShootBall (){
// 	RobotMap.armTalon.set(ControlMode.Position, SHOOT_BALL_POSITION);
// }

// public void moveToIntakeBall (){
// 		RobotMap.armTalon.set(ControlMode.Position, INTAKE_BALL_POSITION);
// }

// public boolean isOnTarget() {
// 	return RobotMap.armTalon.getClosedLoopError(0) <= 0;
// }

// 	public void runUpward (){
// 		armTalon.set(Robot.oi.xBoxController.getRawAxis(5));
// 	double dist = Robot.magEncoder.getDistance();
// 	}