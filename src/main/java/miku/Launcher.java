package miku;

import javafx.application.Application;
import miku.ui.Main;
import miku.utility.Priority;
/**
 * A launcher for the app
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println(Priority.HIGH.compareTo(Priority.LOW));
        Application.launch(Main.class, args);
    }
}
