import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileParser
{
    private String _path = "";                  
    private ArrayList<Task> _taskList; 
    private ArrayList<TaskTemplate> _templateList; 
    
    public FileParser(String path)
    {
        _path = path;
        _taskList = new ArrayList<Task>();
        _templateList = new ArrayList<TaskTemplate>();
    }
    
    public void loadTaskList()
    {
        FileReader reader = null;			
        BufferedReader breader = null;

        try
        {
            reader = new FileReader(_path);
            breader = new BufferedReader(reader);
            
			while(breader.ready())
            {
                String fileLine = breader.readLine();
                String[] command = fileLine.split(" ");
                if( command.length==2 )
                {
                    _taskList.add( new Task( command[0] , Integer.parseInt(command[1]) ));
                }
                if( command[0].equals("GEN") )
                {
                    _templateList.add( new TaskTemplate(command[3],Integer.parseInt(command[4])
                            ,Integer.parseInt(command[1]),Integer.parseInt(command[2]), Integer.parseInt(command[5])));
                }
            }
            
            System.out.println("Wczytano z pliku "+_path);           
        }
        catch(Exception ex)
        {
            System.out.println("Wystąpił błąd wczytywania pliku: "+ex.getMessage());
        }
        finally
        {
			if(breader != null) 
            {
                try
                {
                    breader.close();
                }
                catch(Exception ex)
                {
                    System.out.println("Błąd przy zamykaniu pliku. "+ex.getMessage());
                }
            }
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(Exception ex)
                {
                    System.out.println("błąd przy zamykaniu pliku. "+ex.getMessage());
                }
            }
        }
    }
    
    public ArrayList<Task> getTaskList() 
    {
        return _taskList;
    }
    
    public ArrayList<TaskTemplate> getTemplateList()
    {
                return _templateList;
    }
    
}
