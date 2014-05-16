public class Task
{
    public int sector = 0; 
    public int timeCreated = 0;
    public String id = "";
    
    public Task(String id, Integer sector) // tak jak poprzednio konstruktor dla pocz¹tkowych (niegenerowanych procesów)
    {
    	this(id,sector,0);
    }

    public Task(String id, Integer sector, Integer timeCreated) // w³aœciwy konstruktor z flag¹, kiedy zosta³ stworzony Task
    {
    	this.id = id;
    	this.sector = sector;
    	this.timeCreated = timeCreated;
    }

}