package agave;

import agave.Task.TaskList;
import agave.Util.AgaveException;
import agave.Util.Parser;
import agave.Util.Storage;
import agave.Util.Ui;
import agave.Util.CommandHandler;

import java.io.IOException;

/**
 * The Agave class represents the main application that manages tasks.
 * It handles user input, processes commands, and interacts with the storage system.
 */
public class Agave {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * Constructs an Agave instance with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be stored and loaded from.
     */
    public Agave(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isRunning = true;
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        String response = "";
        Parser parser = new Parser(userInput);

        try {
            response = CommandHandler.handleCommand(parser, tasks, ui);
            storage.saveTasks(tasks.getTasks());
        } catch (AgaveException | IOException e) {
            response = ui.showError(e.getMessage());
        }

        return response;
    }

    /**
     * Starts the application, processes user commands, and manages the task list.
     */
    public void run() {
        ui.showWelcome();

        while (isRunning) {
            String userInput = ui.getUserInput("Enter a command: ");
            String response = getResponse(userInput);
            ui.showMessage(response);

            if (userInput.equals("bye")) {
                isRunning = false;
            }
        }
    }

    public static void main(String[] args) {
        new Agave("./data/agave.txt").run();
    }
}
