package org.usfirst.frc.team5962.robot.subsystems;

import org.usfirst.frc.team5962.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystems;

/** 
 * 
 */
public class ArmMotor extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
WPI_TalonSRX armTalon = RobotMap.armTalon;
	
	public void runUpward (){
		armTalon.set(-0.5);
    double dist = Robot.magEncoder.getDistance();
    if(dist>=48){
			//Drive Motor
		}
	}

	public void runUpwardSlow (){
		armTalon.set(-0.5);
		double dist = Robot.magEncoder.getDistance();
    if(dist>=48){
			//Drive Motor
		}
	}
	
	public void runDownward (){
		armTalon.set(1);
		double dist = Robot.magEncoder.getDistance();
    if(dist>=48){
			//Drive Motor
		}
	}
	
	public void stop (){
		armTalon.set(0);
		double dist = Robot.magEncoder.getDistance();
    if(dist>=48){
			//Drive Motor
		}
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}