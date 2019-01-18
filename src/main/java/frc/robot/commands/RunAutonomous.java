package frc.robot.commands;

public class RunAutonomous extends Command {

	private Autonomous autonomousSubsystem = new Autonomous();
	

	//Constructor for Run Autonomous
	public RunAutonomous() {
	}
	

	//Initializes the timer, switch location
	protected void initialize(){
		
		autonomousSubsystem.init();
		
		
	}

	//Runs until we reach our end goal
	protected void execute() 
	{	
		autonomousSubsystem.elapsedTime();
	
		
	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}
}