
//W pliku niech b�dzie GEN [co ile] [w kt�rym momencie ma si� pojawi�] [id] [sector, w kt�rym ma si� pojawi� (tak? po co? zostawmy w razie w.)] [ile Task�w]

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
        this.remains = remains;
    }
}
