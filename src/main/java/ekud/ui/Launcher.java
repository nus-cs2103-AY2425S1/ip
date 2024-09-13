package ekud.ui;

import ekud.Ekud;
import javafx.application.Application;

/**
 * Launches the EKuD chatbot GUI application.
 *
 * @author uniqly
 */
public class Launcher {
    private static final String ENABLE_CLI_FLAG = "--text";
    private static final String INVALID_FLAG_MESSAGE =
            String.format("Invalid flag, defaulting to GUI mode.\nUse '%s' for CLI mode.", ENABLE_CLI_FLAG);
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals(ENABLE_CLI_FLAG)) {
            Ekud.runTextMode();
            System.exit(0);
        } else if (args.length > 0) {
            System.out.println(INVALID_FLAG_MESSAGE);
        }
        Application.launch(Main.class, args);
    }
}
