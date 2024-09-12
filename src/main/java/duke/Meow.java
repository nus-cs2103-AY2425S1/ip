package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class for the Meow chatbot application.
 * This class initializes the necessary components and manages the main application loop.
 */
public class Meow {

    private final Ui ui = new Ui();
    private final Storage storage = new Storage("tasks.ser");
    private TaskList taskList;
    private final Parser parser = new Parser();

    /**
     * Constructs a Meow application instance.
     * Attempts to load existing tasks from storage. If loading fails, it initializes an empty task list.
     */
    public Meow() {
        try {
            ArrayList<Task> tasks = storage.loadTasks();
            taskList = new TaskList(tasks);
        } catch (IOException | ClassNotFoundException e) {
            ui.showMessage("Error loading tasks: " + e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }


     /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            return parser.read(input, taskList, ui, storage);
        } catch (IOException e) {
            // Handle IOException, e.g., log it or return a specific error message
            return e.getMessage();
        } catch (MeowException e) {
            // Handle MeowException, e.g., log it or return the error message
            return e.getMessage();
        }
    }
}