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
     * Runs the main application loop.
     * Reads user input, processes commands, and handles exceptions until program terminates.
     */

    /*
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String input = ui.readCommand();
            try {
                parser.parse(input, taskList, ui, storage);
                if (input.equals("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }
            } catch (IOException | MeowException e) {
                ui.showMessage("Error: " + e.getMessage());
            }
        }
    }
    */

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

    /**
     * Standard Main function, nothing new
     *
     * @param args Command-line arguments (not used).
     */

    /*
    public static void main(String[] args) {
        new Meow().run();
    }
    */

}