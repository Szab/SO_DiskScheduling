public class SCANSimulation extends Simulation
{
	public int simulationOption = 0;
        public TaskManager taskMan;
        private int kierunek = 0;
    
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
            int tempCurr = taskMan.currentPos;
            int tempKier = kierunek;
            
            if(kierunek!=0)
            {
            for( ; (tempCurr>=0 && tempCurr<taskList.length) && taskList[tempCurr]==null; tempCurr+=kierunek)
            if(tempCurr == 0 || tempCurr == taskList.length-1)
            {
                kierunek *= (-1);               
            }
            }
            else
            {
                kierunek = 1;
            }
            
            taskMan.current = taskList[tempCurr];
           
            taskMan.currentPos += kierunek;
            taskMan.totalJumps++;
            
            
            if(taskList[taskMan.currentPos] != null)
            {
                taskMan.previous = taskMan.current;
                taskMan.current = null;
                taskList[taskMan.currentPos] = null;
                taskMan.numberRealised++;
            }
        }
        
        public SCANSimulation(TaskManager taskManager)
	{
				this.taskMan = taskManager;
                taskList = taskMan.disc.taskList;
	}
}
