package hypebot.main;

import java.io.FileNotFoundException;

import hypebot.command.Command;
import hypebot.parser.CommandParser;
import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.UiCli;

/**
 * The chatbot which the user interacts with.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class HypeBot {
    private StorageManager storage;
    private Tasklist tasks;
    private UiCli uiCli;
    private String commandType;

    /**
     * Creates a new HypeBot.
     *
     * @param filePath The file path for tasks to save and load to.
     */
    public HypeBot(String filePath) {
        uiCli = new UiCli();
        storage = new StorageManager(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
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

    /**
     * Runs the main event loop for HypeBot-user interaction.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = uiCli.readCommand();
                Command c = CommandParser.parse(fullCommand);
                String response = c.execute(tasks, uiCli, storage);
                System.out.println(response);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println(uiCli.showError(e.getMessage()));
            }
        }
    }

    /**
     * Creates a new HypeBot object with the specified file path and runs it.
     *
     * @param args Command-line arguments that remain unused.
     */
    public static void main(String[] args) {
        new HypeBot("./src/main/data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = CommandParser.parse(input);
            commandType = c.getClass().getSimpleName();
            return c.execute(tasks, uiCli, storage);
        } catch (Exception e) {
            commandType = "Error";
            return uiCli.showError(e.getMessage());
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
