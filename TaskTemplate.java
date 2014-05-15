
//W pliku niech bêdzie GEN [co ile] [w którym momencie ma siê pojawiæ] [id] [sector, w którym ma siê pojawiæ (tak? po co? zostawmy w razie w.)] [ile Tasków]

public class TaskTemplate
{
    int sector = 0;
    int occurs = 0;
    int interval = 0;
    int remains = -1;
    String id = "";
    
    TaskTemplate(String idTemplate, int sector, int interval, int occurs, int remains)
    {
        id = idTemplate;
        this.sector = sector;
        this.interval = interval;
        this.occurs = occurs;
        this.remains = -1;
    }
}
