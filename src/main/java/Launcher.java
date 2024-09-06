import barney.Barney;
import barney.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        String mode = "gui";
        String saveFilePath = "list.txt";


        if (args.length >= 1) {
            mode = args[0];
        }

        if (args.length >= 2) {
            saveFilePath = args[1];
        }

        switch (mode) {
        case "gui" -> Application.launch(Main.class, args);
        case "text" -> new Barney(saveFilePath).runSystemOut();
        default -> System.out.println("Invalid argument: " + mode);
        }
    }
}
