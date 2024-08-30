package sigmabot;
import sigmabot.command.Command;
import sigmabot.command.Terminate;
import sigmabot.command.Greeting;
import sigmabot.task.Task;
import sigmabot.ui.UiComponent;

import java.util.Map;

public class Dialogue {
    private String identifier = "default chat";
    private Map<String, Map<String, Task>> taskListCatalog;
    private UiComponent ui = new UiComponent();
    private Dialogue() {}
    private Dialogue(String identifier) {
        this.identifier = identifier;
    };
    public static Dialogue defaultDialogue() {
        return new Dialogue();
    }
    public static Dialogue newNamedDialogue(String identifier) {
        return new Dialogue(identifier);
    }

    public void run() {
        Greeting.greet(ui); // Initial greeting to the user
        Command command;
        // Loop until a Terminate command is encountered
        do {
            ui.printDialogue("Enter a command: ");
            command = ui.readCommand(); // Read command from user input
            command.execute(ui); // Execute the command
        } while (!(command instanceof Terminate)); // Continue until the command is Terminate

        ui.closeScanner(); // Close the scanner before exiting
    }
}
