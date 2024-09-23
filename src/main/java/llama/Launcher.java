package llama;

import javafx.application.Application;

/**
 * A Launcher class to work around class path issues (entry point)
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
