package frc.robot.commands;
import frc.robot.subsystems.Autonomous;
import edu.wpi.first.wpilibj.command.Command;



public class RunAutonomous extends Command {

	private Autonomous auto = new Autonomous();

	//Constructor for Run autonomous
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
