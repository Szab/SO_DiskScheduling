
public class Disc 
{
	public TaskManager taskMan = null; //referencja do TaskManagera, ¿eby móc u¿ywaæ jego pól
	public Task[] taskList = null; // nasza tablica sektorów
	
	public boolean isDone()
	{
		for(Task task : taskList) // je¿eli gdziekolwiek jest Task, to nie jest skoñczona symulacja dysku.
			if(task != null)
				return false;
		return true;
	}

	public void add(int sector, Task task) // dodanie na pozycjê, konretnego Task'u
	{
		if(sector<taskList.length)
			taskList[sector] = task;
	}
	
	public Disc(int size, TaskManager taskMan) //Konstruktor
	{
		taskList = new Task[size];
		this.taskMan = taskMan;
	}
}
