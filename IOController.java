/* Klasa IOController:
- Wyświetlanie statystyk i obecnego stanu symulacji
- Interakcja z użykownikiem
*/
import java.lang.Thread;
public class IOController
{   
    private ProcessManager procMan = null;   // Referencja do ProcessManagera
    private SimulationGUI GUI = null;       // Referencja do GUI
    
    
    // Konstruktor
    public IOController(ProcessManager procMan)
    {
        this.procMan = procMan;
    }
    
    // Inicjalizator GUI
    public void initialize()
    {
        GUI = new SimulationGUI(procMan, this);
        
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(SimulationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SimulationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SimulationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SimulationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                GUI.setVisible(true);
            }
        });
    }
       
    // Aktualizacja statystyk
    public void update()
    {        
        GUI.update();      
        
    }
    
    // Aktualizuje ilość wygenerowanych procesów
    public void updateGeneratedCount()
    {
        GUI.incrementGenerated();
    }
}
