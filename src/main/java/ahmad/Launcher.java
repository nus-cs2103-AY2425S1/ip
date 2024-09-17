package ahmad;

import java.util.Arrays;

import javafx.application.Application;

/**
 * Launcher class for the application
 */
public class Launcher {
    /**
     * Runs the program.
     * Loads file (if exists), and starts interaction.
     *
     * @param args Arguments from the compiler.
     */
    public static void main(String... args) {


        if (!Arrays.asList(args).contains("--without-file")) {
            Storage.load();
        }

        Application.launch(Ahmad.class, args);
    }
}
