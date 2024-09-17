package alexer;

import alexer.ui.Gui;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Alexer alexer = new Alexer();
        alexer.start();
        Application.launch(Gui.class, args);
    }
}
