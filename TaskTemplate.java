/* Klasa ProcessTemplate: szablon struktury procesu dla generatora procesów
PRZYKŁAD UŻYCIA W PLIKACH:
GEN [interwal] [ile razy] [id] [dlugosc] */

public class ProcessTemplate
{
    int duration = 0;  // Czas trwania procesu
    int interval = 1;  // Interwał generowania procesów
    int remaining = -1;
    String id = "";    // Wzorzec identyfikatorów
    
    ProcessTemplate(String idTemplate, int duration, int interval, int remaining)
    {
        id = idTemplate;
        this.duration = duration;
        this.interval = interval;
        this.remaining = remaining;
    }
}
