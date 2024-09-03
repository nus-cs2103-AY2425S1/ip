package Main;

import java.io.FileNotFoundException;

import Commands.Command;
import Exception.DiegoException;
import Tasks.TaskList;


/**
 * The main class for the Diego task manager application.
 */
public class Diego {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Diego application.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Diego(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        storage.createFileIfNotExists();

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
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
