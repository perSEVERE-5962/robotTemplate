package frc.robot.commands;
import frc.robot.subsystems.Autonomous;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RunAutonomous extends Command {
	private Autonomous auto = new Autonomous();

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
	protected void excute() 
	{	
		if(auto.isStep1Done() == false){
			Robot.logger.putMessage("Running autonomous step #1");
			auto.Step1();
		}
		else if(auto.isStep2Done() == false){
			Robot.logger.putMessage("Running autonomous step #2");
			auto.Step2();
		}
		else if(auto.isStep3Done() == false){
			Robot.logger.putMessage("Running autonomous step #3");
			auto.Step3();
		}
		else if(auto.isStep4Done() == false){
			Robot.logger.putMessage("Running autonomous step #4");
			auto.Step4();
		}
		else if(auto.isStep5Done() == false){
			Robot.logger.putMessage("Running autonomous step #5");
			auto.Step5();
		}
		else if(auto.isStep6Done() == false){
			Robot.logger.putMessage("Running autonomous step #6");
			auto.Step6();
		}				
		else if(auto.isStep7Done() == false){
			Robot.logger.putMessage("Running autonomous step #7");
			auto.Step7();
		}
		/*
			STEP8 = place the game piece
		*/
		// else if(auto.isStep9Done() == false){
		// 	auto.step9();
		// }	
		// else if(auto.isStep10Done() == false){
		// 	auto.Step10();
		// }		
		// else if(auto.isStep11Done() == false){
		// 	auto.step11();
		// }	

	//	auto.elapsedTime();
	
		
	}
	public void run(){
		execute();
	}

	@Override
	protected boolean isFinished()
	{
		// TODO auto-generated method stub
		return false;
	}
}	
