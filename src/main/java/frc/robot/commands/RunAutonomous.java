package frc.robot.commands;
import frc.robot.subsystems.Autonomous;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RunAutonomous extends Command {

	public RunAutonomous(Robot.StartingPosition startingPosition, Robot.TargetPosition targetPosition, Robot.GamePiece gamePiece) {
		SmartDashboard.putString("Starting Position", startingPosition.toString());
		SmartDashboard.putString("Target Position", targetPosition.toString());
		SmartDashboard.putString("Game Piece", gamePiece.toString());
	private Autonomous auto = new Autonomous();
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
			auto.Step1();
		}
		else if(auto.isStep2Done() == false){
			auto.Step2();
		}
		else if(auto.isStep3Done() == false){
			auto.Step3();
		}
		else if(auto.isStep4Done() == false){
			auto.Step4();
		}
		else if(auto.isStep5Done() == false){
			auto.Step5();
		}
		else if(auto.isStep6Done() == false){
			auto.Step6();
		}				
		else if(auto.isStep7Done() == false){
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
