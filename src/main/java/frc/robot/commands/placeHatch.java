package frc.robot.commands;
import frc.robot.*;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
public class placeHatch extends Command{
public void moveToPlaceHatch (){
    double PLACE_HATCH_POSITION = 0.9*4096;
    RobotMap.armTalon.set(ControlMode.Position, PLACE_HATCH_POSITION);
    Robot.oi.hatch = true;
}
protected boolean isFinished(){
    return (Robot.oi.hatch = false);
}
}