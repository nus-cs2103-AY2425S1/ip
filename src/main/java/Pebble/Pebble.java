package pebble;

import java.io.IOException;

/**
 * A chatbot that helps to handle tasks.
 * Tasks including ToDos, Events, Deadlines.
 * Equipped with basic functionalities to manipulate the task list.
 */
public class Pebble {
    /** instance to handle user interface */
    private Ui ui;
    /** instance to handle local storage of tasks list */
    private Storage storage;
    /** A list to store tasks */
    private TasksList tasksList;

    /**
     * Initialises the whole program with the data file.
     *
     * @param filePath Data file that contains the tasks list.
     */
    public Pebble(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TasksList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            tasksList = new TasksList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        // Parse the command
        Command command = Parser.parseCommand(input);
        // Execute the command and get the response for GUI
        return command.execute(tasksList, ui, storage);
    }
}
