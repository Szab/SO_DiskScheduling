public abstract class Simulation 
{
	public int simulationOption = 0;
    public TaskManager taskMan;
    
	public Simulation(TaskManager taskManager)
	{
		this.taskMan = taskManager;
	}
	public abstract boolean isDone();
	public abstract void serve();
}
