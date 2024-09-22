package shoai;

import javafx.application.Application;

/**
 * The main entry point for launching the ShoAI application.
 * This class simply delegates to MainApp to avoid the JavaFX runtime issue.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
