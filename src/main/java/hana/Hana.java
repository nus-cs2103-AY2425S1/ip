package hana;

import java.util.ArrayList;

import hana.command.Command;
import hana.parser.Parser;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * The main class for the Hana chatbot.
 * Handles the initialization, running, and termination of the application.
 */
public class Hana {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Hana application instance.
     * Initializes the UI, task list, and storage.
     * Attempts to load tasks from storage.
     */
    public Hana() {
        ui = new Ui();
        tasks = new TaskList(new ArrayList<>());
        storage = new Storage(tasks.getTasks());

        // Assert that important components are initialized
        assert ui != null : "Ui is not initialized";
        assert tasks != null : "TaskList is not initialized";
        assert storage != null : "Storage is not initialized";
        try {
            storage.load();
        } catch (HanaException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user input.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            storage.save();
            return ui.getResponse();
        } catch (HanaException e) {
            return e.getMessage();
        }
    }
}
