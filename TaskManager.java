import java.util.ArrayList;
import java.util.UUID;

public class TaskManager
{
    public int numberRealised = 0; //liczba zrealizowanych Task'ów
    public int totalJumps = 0; // liczba zrealizowanych skoków (od previous do current)
    public int discVolume = 0; // sztywna liczba sektorów na dysku
    public int workTime = 0; // łączny czas pracy
    public int currentPos = 50; // aktualna pozycja głowicy dysku (domyślnie 50)
    
    public Disc disc; // pole Disc jako ułatwienie patrz. dokumentacja klasy Disc
    
    private Task current = null; //aktualny Task
    private Task previous = null; // poprzedni task
    
    private boolean _locked = false; // blokada generatora
                                               
    public ArrayList<TaskTemplate> _templateList; // lista szablonów dodawania Task'ów do symulacji
    
    
    private Simulation _simulation; 
    private IOController controller; 
    
    public double getAverageTime()
    {
        return numberRealised==0 ? 0 : (double)totalJumps/(double)numberRealised;
    }

    public void lockProcessGenerator()
    {
        _locked = true;
    }
    
    
    public void unlockProcessGenerator()
    {
        _locked = false;
    }
    
    public void nextStep() //kolejny krok (jednostka czasu)
    {        
        taskGenerator();
        if(!disc.isDone()) _simulation.serve();
        workTime++;
        controller.update(); 
    }
        
    public void nextTask() //przeskoczenie do kolejnego Task'u
    {
        int currentRealised = numberRealised;       
        while(currentRealised==numberRealised && !disc.isDone() )
        {
            _simulation.serve();
            workTime++;
            taskGenerator();
        }
        controller.update(); 
    }
 
    public void endSimulation() //przeskoczenie do końca symulacji
    {       
        while(!disc.isDone())
        {
        	_simulation.serve();
        	workTime++;
        }
        controller.update(); 
    }

    private void taskGenerator()
    {
    	if(_locked) return;
        for(TaskTemplate template : _templateList)
        {
        	//jeżeli nastąpił czas wywołania (pierwszego pojawienia się Task'a, lub po pojawieniu się następuje interwał jego pojawiania się
            if(template.occurs == workTime || (template.occurs > workTime && workTime%template.interval == 0))
            {
                controller.updateGeneratedCount();
                String randomID = template.id
                        +UUID.randomUUID().toString();
                //mam nadzieję, że nie każesz mi tego tłumaczyć, mam nadzieję, że działa ;*
                if(disc.taskList[template.sector] == null)
                {
                Task newTask = new Task(randomID,template.sector, workTime);
                disc.add(template.sector, newTask);
                }else{
                	boolean wasAdded = false;
                	int sectorNew = template.sector - 1;
                	while(!wasAdded && sectorNew != template.sector)
                	{
                		if(sectorNew != 0 && disc.taskList[sectorNew] == null)
                		{
                			Task newTask = new Task(randomID, sectorNew, workTime);
                			disc.add(sectorNew,newTask);
                			wasAdded = true;
                		}else if(sectorNew != 0){
                			sectorNew--;
                		}else if(sectorNew == 0){
                			sectorNew = discVolume;
                		}else if(sectorNew > template.sector){
                			sectorNew--;
                		}
                	}
                }
            }
        }
    }
    public void initialize(Simulation sim)
    {
        _simulation = sim;
        controller.initialize();
    }
    
    public TaskManager(int size, ArrayList<TaskTemplate> templateList)
    {
    	this.discVolume = size;
        this.disc = new Disc(size,this);
        _templateList = templateList;
        controller = new IOController(this);
    }
    
}
