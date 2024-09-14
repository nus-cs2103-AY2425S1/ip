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

    /**
     * Creates a new HypeBot.
     *
     * @param filePath The file path for tasks to save and load to.
     */
    public HypeBot(String filePath) {
        uiCli = new UiCli();
        storage = new StorageManager(filePath);
        try {
            uiCli.showLoadingTasks();
            tasks = storage.load();
            uiCli.showGreeting();
        } catch (FileNotFoundException e) {
            uiCli.showError(e.getMessage());
            tasks = new Tasklist();
        }
    }

    /**
     * Runs the main event loop for HypeBot-user interaction.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = uiCli.readCommand();
                uiCli.showDividerLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(tasks, uiCli, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                uiCli.showError(e.getMessage());
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
}
