package rudolf;

import rudolf.parser.Parser;
import rudolf.storage.Storage;
import rudolf.task.TaskList;
import rudolf.ui.Ui;

import java.io.IOException;

/**
 * Represents the main application class for Rudolf, which handles the initialization,
 * user interactions, and task management.
 * It connects the user interface, task storage, and command parsing components.
 */
public class Rudolf {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Initializes the Rudolf application with the specified file path for task storage.
     * Sets up the user interface, storage, task list, and parser.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Rudolf(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty";

        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("Error encountered while loading previous tasks.");
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    /**
     * Gets greeting message.
     *
     * @return The greeting message from Rudolf.
     */
    public String getGreeting() {
        assert ui != null : "Ui should be initialized before calling getGreeting";
        String greeting = ui.showGreeting();
        assert greeting != null && !greeting.trim().isEmpty() : "Greeting should not be null or empty";
        return greeting;
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input as a string.
     * @return The response generated by Rudolf.
     */
    public String getResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";

        String response = parser.parse(input);
        assert response != null : "Parser should return a non-null response";

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            response += "\n" + ui.showSaveError(e.getMessage());
        }

        return response;
    }
}




