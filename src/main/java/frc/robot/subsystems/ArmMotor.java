package frc.robot.subsystems;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/** 
 * 
 */
public class ArmMotor extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
WPI_TalonSRX armTalon = RobotMap.armTalon;
	
	public void runUpward (){
		armTalon.set(Robot.oi.xBoxController.getRawAxis(5));
    double dist = Robot.magEncoder.getDistance();
    if(dist>=48){
			//Drive Motor
		}
	}

	public void runUpwardSlow (){
		armTalon.set(Robot.oi.xBoxController.getRawAxis(5));
		double dist = Robot.magEncoder.getDistance();
    if(dist>=48){
			//Drive Motor
		}
	}
	
	public void runDownward (){
		armTalon.set(Robot.oi.xBoxController.getRawAxis(5));
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