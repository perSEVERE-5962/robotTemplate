package frc.robot.commands;
import frc.robot.subsystems.Autonomous;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;


public class RunAutonomous extends Command {

	private Autonomous autonomousSubsystem = new Autonomous();
	

	//Constructor for Run Autonomous
	public RunAutonomous() {
	}
	

	//Initializes the timer, switch location
	protected void initialize(){
		
		//autonomousSubsystem.init();
		
		
	}

	//Runs until we reach our end goal
	//public boolean Step1_done;
	protected void execute() 
	{	
		if(autonomousSubsystem.Step1_done == false){
			autonomousSubsystem.Step1();
		}
		else if(autonomousSubsystem.Step2_done == false){
			autonomousSubsystem.Step2();
		}
		else if(autonomousSubsystem.Step3_done == false){
			autonomousSubsystem.Step3();
		}
		else if(autonomousSubsystem.Step4_done == false){
			autonomousSubsystem.Step4();
		}
		else if(autonomousSubsystem.Step5_done == false){
			autonomousSubsystem.Step5();
		}
		else if(autonomousSubsystem.Step6_done == false){
			autonomousSubsystem.Step6();
		}				

	//	autonomousSubsystem.elapsedTime();
	
		
	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}
}	
execute (){
	if (Step1-done==false)
		Step1();
	else if (Step2-done==false)
		Step2();
	else if (Step3-done==false)
		Step3();
	else if (Step4-done==false)
		Step4();
	else if (Step5-done==false)
		Step5();
	else
		initialize();
}