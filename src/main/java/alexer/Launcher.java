package alexer;

import alexer.ui.Gui;
import javafx.application.Application;

public class Launcher {
    /**
     * Launches the GUI application for Alexer.
     * @param args the command line arguments passed
     */
    public static void main(String[] args) {
        Alexer alexer = new Alexer();
        Application.launch(Gui.class, args);
    }
}
