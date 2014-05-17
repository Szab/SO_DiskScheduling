public class FCFSSimulation extends Simulation
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
        
        private Task findFirst()
        {
            Task shortest = null;
            for(int i = 0 ; i<taskList.length ; i++)
            {
                if(shortest == null) shortest = taskList[i];
                else
                {
                    if(taskList[i] != null && shortest.timeCreated>taskList[i].timeCreated) shortest = taskList[i];
                }
            }
            return shortest;
        }
        
        @Override
	public void serve()
        {
            if(taskMan.current == null) 
            {
                taskMan.current = findFirst();
                int pos = 0;
                for( ; taskList[pos] != taskMan.current ; pos++);
                kierunek = (int)Math.signum(pos - taskMan.currentPos);
            }
            taskMan.currentPos += kierunek;
            taskMan.totalJumps++;
            
            if(taskList[taskMan.currentPos] == taskMan.current)
            {
                taskMan.previous = taskMan.current;
                taskMan.current = null;
                taskList[taskMan.currentPos] = null;
                taskMan.numberRealised++;
            }
        }
        
        public FCFSSimulation(TaskManager taskManager)
	{
				this.taskMan = taskManager;
                taskList = taskMan.disc.taskList;
	}
}
