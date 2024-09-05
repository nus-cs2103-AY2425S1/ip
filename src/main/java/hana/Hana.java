package hana;

import java.io.IOException;

import hana.task.TaskList;

/**
 * Entry point for the Hana application.
 * Manages the initialization and execution of the program.
 */
public class Hana {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Hana application with the given file path.
     * Loads the task list from the specified file.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Hana(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | HanaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Hana application, which listens for user input and executes commands.
     * Not used in GUI
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HanaException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Processes a single user command and returns the response as a string.
     *
     * @param input The user's command input.
     * @return The response of Hana based on the user command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return ui.getResponseMessage(); // A method in Ui class to return response message
        } catch (HanaException | IOException e) {
            return e.getMessage();
        }
    }

}
