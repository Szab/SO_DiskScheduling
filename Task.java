public class Task
{
    public int sector = 0; 
    public int timeCreated = 0;
    public String id = "";
    
    public Task(String id, Integer sector) // tak jak poprzednio konstruktor dla pocz�tkowych (niegenerowanych proces�w)
    {
    	this(id,sector,0);
    }

    public Task(String id, Integer sector, Integer timeCreated) // w�a�ciwy konstruktor z flag�, kiedy zosta� stworzony Task
    {
    	this.id = id;
    	this.sector = sector;
    	this.timeCreated = timeCreated;
    }

}