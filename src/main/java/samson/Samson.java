package samson;

import samson.command.Command;
import samson.task.TaskList;

import java.io.IOException;

/**
 * The <code> Samson </code> class is the main entry point for the Samson application.
 * It initializes the application, handles user input, and controls the flow
 * of the application by executing commands.
 */
public class Samson {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new <code> Samson </code> instance, initializing the user interface,
     * loading tasks from the storage file, and handling any potential errors that occur
     * during initialization.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Samson(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTaskFromFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (SamException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the Samson chatbot, continuously reading user input,
     * parsing it into commands, and executing those commands until the user decides to exit.
     */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SamException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while accessing the storage.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method is the entry point of the Samson application.
     * It creates a new <code> Samson </code> instance and starts the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Samson("data/samson.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String res = c.execute(tasks, ui, storage);
            return res;
        } catch (SamException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
