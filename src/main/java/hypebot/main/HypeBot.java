package hypebot.main;

import java.io.FileNotFoundException;
import java.text.MessageFormat;

import hypebot.command.Command;
import hypebot.parser.CommandParser;
import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

import static hypebot.common.Messages.ERROR_FIX_CORRUPTED_TASK;
import static hypebot.common.Messages.ERROR_LOAD_TASK;

/**
 * The chatbot which the user interacts with.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class HypeBot {
    private final StorageManager storage;
    private final UiCli uiCli;
    private final CommandParser commandParser;
    private Tasklist tasks;
    private String commandType;
    private String bootingErrorMessage;

    /**
     * Creates a new HypeBot.
     *
     * @param filePath The file path for tasks to save and load to.
     */
    public HypeBot(String filePath) {
        uiCli = new UiCli();
        storage = new StorageManager(filePath);
        commandParser = new CommandParser();
        try {
            tasks = storage.load();
        } catch (RuntimeException | FileNotFoundException e) {
            commandType = "Error";
            bootingErrorMessage = uiCli.showError(MessageFormat.format(
                    "{0}{1}{2}", ERROR_LOAD_TASK, e.getMessage(), ERROR_FIX_CORRUPTED_TASK
            )).show();
            tasks = new Tasklist();
        }
    }

    public StorageManager getStorage() {
        return storage;
    }

    public UiCli getUiCli() {
        return uiCli;
    }

    public Tasklist getTasks() {
        return tasks;
    }

    public String getResponse(String input) {
        bootingErrorMessage = null;
        try {
            Command c = commandParser.parse(input);
            commandType = c.getClass().getSimpleName();
            return c.execute(tasks, uiCli, storage).show();
        } catch (Exception e) {
            commandType = "Error";
            return uiCli.showError(e.getMessage()).show();
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public boolean hasBootingError() {
        return bootingErrorMessage != null;
    }

    public String getBootingErrorMessage() {
        return bootingErrorMessage;
    }
}
