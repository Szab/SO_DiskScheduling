public abstract class Simulation 
{
	public int simulationOption = 0;
        public TaskManager taskMan;
        protected Task[] taskList = null;
    
        /*
	public Simulation(TaskManager taskManager)
	{
		this.taskMan = taskManager;
                taskList = taskMan.disc.taskList;
	}
        */
	public abstract boolean isDone();
	public abstract void serve();
}
