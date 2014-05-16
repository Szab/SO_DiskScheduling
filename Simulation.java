/* 
 * abstrakcyjna klasa Simulation, b�d�ca "szablonem" dla poszczeg�lnych symulacji
 * w konstruktorze przekazywana jest referencja do ProcessManager'a, 
 * �eby zachowa� ich wsp�dzia�anie 
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
	// metoda, okre�laj�ca, kiedy symulacja jest sko�czona
	public abstract boolean isDone();
	//metoda wykonawcza symulacji, wykonuje current (w jednosce czasu)
	public abstract void serve();
}
