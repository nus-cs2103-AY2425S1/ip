package monobot;

import monobot.exception.MonoBotException;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

/**
 * Represents entry point of application.
 */
public class MonoBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the Ui, TaskList and Storage of the chatbot.
     *
     * @param filePath Path to the file where tasks will be stored.
     */
    public MonoBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MonoBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public static void main(String[] args) {
        MonoBotGui.main(args);
    }
}
