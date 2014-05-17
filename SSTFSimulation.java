public class SSTFSimulation extends Simulation
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
        
        private Task findClosest()
        {
            Task task1 = null, task2 = null;
            int task1pos = 0, task2pos = 0;
            for(int i = 0 ; i<taskMan.currentPos ; i++)
            {
                if(taskList[i] != null) 
                {
                    task1 = taskList[i];
                    task1pos = i;
                }
            }
            for(int i = taskList.length-1 ; i>taskMan.currentPos ; i--)
            {
                if(taskList[i] != null) 
                {
                    task2 = taskList[i];
                    task2pos = i;
                }
            }
            if(task1 == null) return task2;
            if(task2 == null) return task1;
            if(taskMan.currentPos-task1pos == task2pos-taskMan.currentPos)
                return kierunek>0 ? task2 : task1;
            return taskMan.currentPos-task1pos > task2pos-taskMan.currentPos ? task2 : task1;
        }
        
        @Override
	public void serve()
        {
            if(taskMan.current == null) 
            {
                taskMan.current = findClosest();
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
        
        public SSTFSimulation(TaskManager taskManager)
	{
				this.taskMan = taskManager;
                taskList = taskMan.disc.taskList;
	}
}
