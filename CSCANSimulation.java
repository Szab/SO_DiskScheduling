public class CSCANSimulation extends Simulation
{
	public int simulationOption = 0;
        public TaskManager taskMan;
        private int kierunek = 1;
    
        @Override
	public boolean isDone()
        {
            boolean isDone = true;
            for(int i = 0 ; i<taskMan.disc.taskList.length ; i++)
            {
                if(taskList[i]!=null)
                {
                    return false;
                }
            }
            return true;
        }
        
        
        @Override
	public void serve()
        {
            taskMan.currentPos += kierunek;
            for(int i=taskMan.currentPos ; i<taskList.length ; i++)
            {
                if(taskList[i] != null)
                {
                    taskMan.current = taskList[i];
                    break;
                }
                else if(i == taskList.length-1) 
                {
                    taskMan.currentPos = 0;
                }
            }
            
            
            taskMan.totalJumps++;            
            
            if(taskList[taskMan.currentPos] != null)
            {
                taskMan.previous = taskMan.current;
                taskMan.current = null;
                taskList[taskMan.currentPos] = null;
                taskMan.numberRealised++;
            }
        }
        
        public CSCANSimulation(TaskManager taskManager)
	{
				this.taskMan = taskManager;
                taskList = taskMan.disc.taskList;
	}
}
