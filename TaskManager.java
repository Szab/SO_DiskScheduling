/* Klasa ProcessManager:
- Obsługa generatora procesów
- Zliczanie wykonań
- Generowanie statystyk
- Wybór algorytmu
*/

import java.util.ArrayList;
import java.util.UUID;

public class ProcessManager
{
    public int numberRealised = 0; // Liczba w pełni zrealizowanych procesów
    public int workTime = 0; // Liczba zrealizowanych kwantów czasu
    public int overallWaited = 0; // Suma czasów oczekiwania na wykonanie
    public int quantSize = 0;  // Długość kwantu czasu dla ROT
    
    private boolean _locked = false;
    
    public ArrayList<Process> processList; // Lista procesów do zrealizowania
                                             // PAMIĘTAĆ O ZACHOWANIU KOLEJNOŚCI
    public ArrayList<ProcessTemplate> _templateList; // Lista szablonów dla generatora
    
    private Simulation _simulation; // Obecnie wykonywana symulacja
    private IOController controller; // Kontroler wejścia/wyjścia
    
    // Zwraca sredni czas oczekiwania na realizacje
    public double getAverageTime()
    {
        return numberRealised==0 ? 0 : (double)overallWaited/(double)numberRealised;
    }
    
    // Obsługa procesu
    
    // Pojedynczy krok
    public void nextStep()
    {        
        processGenerator();
        if(processList.size()!=0) _simulation.serve();
        controller.update(); 
    }
        
    // Nastepny proces
    public void nextProcess()
    {
        int currentRealised = numberRealised;       
        while(currentRealised==numberRealised && processList.size()!=0)
        {
            _simulation.serve();
            processGenerator();
        }
        controller.update(); 
    }
    
    // Zakoncz symulacje
    public void endSimulation()
    {       
        while(processList.size()!=0)
        {
            _simulation.serve();
        }
        controller.update(); 
    }
    
    // Blokada generatora procesów
    public void lockProcessGenerator()
    {
        _locked = true;
    }
    
    
    // Odblokowanie generatora procesów
    public void unlockProcessGenerator()
    {
        _locked = false;
    }
    
    // Dodaje generowane procesy do listy
    private void processGenerator()
    {
        if(_locked) return;
        for(ProcessTemplate template : _templateList)
        {
            if(template.remaining!=0 && workTime%template.interval == 0)
            {
                controller.updateGeneratedCount();
                String randomID = template.id
                        +UUID.randomUUID().toString(); // Generowanie losowego ID
                Process newProcess = new Process(randomID,template.duration, workTime);
                processList.add(newProcess);
                template.remaining--;
            }
        }
    }
    
    // Pobiera obecnie realizowany proces
    public Process getCurrent()
    {
        return _simulation.current;
    }
    
    // Inicjalizuje PM
    public void initialize(Simulation sim)
    {
        _simulation = sim;
        controller.initialize();
    }
    
    public ProcessManager(ArrayList<Process> processList, ArrayList<ProcessTemplate> templateList)
    {
        this.processList = processList;
        _templateList = templateList;
        controller = new IOController(this);
    }
    
}
