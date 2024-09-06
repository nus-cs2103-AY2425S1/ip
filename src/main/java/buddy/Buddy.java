package buddy;

import java.io.IOException;

import command.Command;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents the Buddy chatbot.
 */
public class Buddy {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Creates a new Buddy chatbot.
     *
     * @param filePath The file path to store the tasks.
     */
    public Buddy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        this.tasks = loadedTasks;
    }

    /**
     * Runs the Buddy chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
        ui.showGoodbye();
    }

    /**
     * Returns the welcome message.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Returns the goodbye message.
     */
    public String getGoodbye() {
        return ui.showGoodbye();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        return command.execute(tasks, ui, storage);
    }
}
