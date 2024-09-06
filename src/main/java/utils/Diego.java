package utils;


import java.io.FileNotFoundException;

import commands.Command;
import exception.DiegoException;
import tasks.TaskList;
/**
 * Main class for the Diego task manager application.
 */
public class Diego {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a new Diego application.
     *
     * @param filePath File path where tasks are stored.
     */
    public Diego(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";

        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        storage.createFileIfNotExists();

        tasks = loadTasksFromStorage();
    }

    private TaskList loadTasksFromStorage() {
        try {
            return new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    public String showWelcomeMessage() {
        return ui.showWelcomeMessage();
    }

    public String showGoodbyeMessage() {
        return ui.showGoodbyeMessage();
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (DiegoException e) {
            return ui.showError(e.getMessage());
        }
    }
}
