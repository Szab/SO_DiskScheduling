import java.util.ArrayList;
import java.util.*;
public class Main
{

    public static void main(String[] args)
    {
        FileParser parser = new FileParser("D:/procesy.txt");
        parser.loadTaskList();
        ArrayList<Task> lista = parser.getTaskList();
        ArrayList<TaskTemplate> lista2 = parser.getTemplateList();
        
        //!! tutaj powinien byæ rozmiar dysku przekazywany, ustawiê póki co na sztywno 200
        TaskManager manager = new TaskManager(200,lista,lista2);
        // co teraz ma robiæ manager, co ma siê dziaæ?
    }
    
}
