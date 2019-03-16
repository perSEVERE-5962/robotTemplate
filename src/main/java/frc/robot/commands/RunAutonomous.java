package frc.robot.commands;
import frc.robot.subsystems.Autonomous;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RunAutonomous extends Command {
	private Autonomous auto = new Autonomous();
	//private Autonomous auto = Robot.ato;
	private boolean isFinished = false;
	// public RunAutonomous(Robot.StartingPosition startingPosition, Robot.TargetPosition targetPosition, Robot.GamePiece gamePiece) {
	// 	SmartDashboard.putString("Starting Position", startingPosition.toString());
	// 	SmartDashboard.putString("Target Position", targetPosition.toString());
	// 	SmartDashboard.putString("Game Piece", gamePiece.toString());
	// }
	public RunAutonomous() {

	}	

	//Initializes the timer, switch location
	protected void initialize(){
		
		//auto.init();
		
		
	}

	//Runs until we reach our end goal
	//public boolean Step1_done;
	protected void execute() 
	{	
		// if(auto.isStep1Done() == false){
		// 	Robot.logger.putMessage("Running autonomous step #1");
		// 	auto.Step1();
		// }else
		if(auto.isStep2Done() == false){
			Robot.logger.putMessage("Running autonomous step #2");
			auto.Step2();
		}
		else if(auto.isStep2Done() == true && auto.isStep3Done() == false){
			Robot.logger.putMessage("Running autonomous step #3");
			auto.Step3();
		}
		else if(auto.isStep3Done() == true && auto.isStep4Done() == false){
			Robot.logger.putMessage("Running autonomous step #4");
			auto.Step4();
		}
		else if(auto.isStep4Done() == true && auto.isStep5Done() == false){
			Robot.logger.putMessage("Running autonomous step #5");
			auto.Step5();
		}
		else if(auto.isStep5Done() == true && auto.isStep6Done() == false){
			Robot.logger.putMessage("Running autonomous step #6");
			auto.driveToHatch();
		 }	
		 else if(auto.isStep6Done() == true && auto.isStep7Done() == false){
			 Robot.logger.putMessage("Running autonomous step for placing the hatch");
			auto.placeHatch();
		 }
		 else if(auto.isStep7Done() == true && auto.isStep8Done() == false){
			Robot.logger.putMessage("Running final autonomous step backup!!");
			 auto.backup();
		 }
		 else{
			 isFinished =true;
		 }			
		
	}

	@Override
	protected boolean isFinished()
	{
		// TODO auto-generated method stub
		return isFinished;
	}
	protected void end() {
	}
  
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		
	}
}	
