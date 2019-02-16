package frc.robot.commands;
import frc.robot.*;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
public class placeHatch extends Command{
    double PLACE_HATCH_POSITION = 0.9*4096;

public boolean onTarget(){
    if(Math.abs(RobotMap.armTalon.getClosedLoopError())<100){
        return true;
    }
    return false;
}
public void moveToPlaceHatch (){
    RobotMap.armTalon.set(ControlMode.Position, PLACE_HATCH_POSITION);
}
public placeHatch() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      moveToPlaceHatch();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }
  protected boolean isFinished(){
      if(onTarget()){
         return true;
      }
      else{
        return false;
      }
  }
}