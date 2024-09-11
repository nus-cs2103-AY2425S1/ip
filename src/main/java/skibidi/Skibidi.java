package skibidi;

import java.io.IOException;
import java.util.Scanner;

import storage.TaskStorage;

/**
 * Represents the main class of the Skibidi program.
 */
public class Skibidi {
    private final Ui ui;
    private final Parser parser;
    private TaskStorage storage;

    /**
     * Creates a new Skibidi program.
     */
    public Skibidi() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new TaskStorage("data/tasks.txt");
        } catch (IOException e) {
            ui.outputMessage("Error loading tasks: " + e.getMessage());
            storage = null;
        }
        assert ui != null : "Ui should not be null";
        assert parser != null : "Parser should not be null";
        assert storage != null : "TaskStorage should not be null";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return ui.outputMessage("Erm...\n" + command.execute(ui, storage));
        } catch (SkibidiException e) {
            return e.getMessage();
        }
    }
}
