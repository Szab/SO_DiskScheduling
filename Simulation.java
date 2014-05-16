/* 
 * abstrakcyjna klasa Simulation, bêd¹ca "szablonem" dla poszczególnych symulacji
 * w konstruktorze przekazywana jest referencja do ProcessManager'a, 
 * ¿eby zachowaæ ich wspó³dzia³anie 
 */
public abstract class Simulation 
{
	// aktualny proces
    public Process current = null;
    // opcja symulacji
	public int simulationOption = 0;
	//ww. referencja
    public ProcessManager procMan;
	//kontruktor
	public Simulation(ProcessManager processManager)
	{
		procMan = processManager;
	}
	// metoda, okreœlaj¹ca, kiedy symulacja jest skoñczona
	public abstract boolean isDone();
	//metoda wykonawcza symulacji, wykonuje current (w jednosce czasu)
	public abstract void serve();
}
