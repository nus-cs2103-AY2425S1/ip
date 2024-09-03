package ekud.ui;

import ekud.Ekud;
import javafx.application.Application;

/**
 * Launches the EKuD chatbot GUI application.
 *
 * @author uniqly
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--text")) {
            Ekud.runTextMode();
            System.exit(0);
        } else if (args.length > 0) {
            System.out.println("Invalid flag, defaulting to GUI mode.\nUse '--text' for CLI mode.");
        }
        Application.launch(Main.class, args);
    }
}
