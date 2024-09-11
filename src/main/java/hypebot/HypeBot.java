package hypebot;

import hypebot.command.Command;
import hypebot.parser.Parser;
import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

import java.io.FileNotFoundException;

/**
 * The chatbot which the user interacts with.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class HypeBot {
    private StorageManager storage;
    private Tasklist tasks;
    private Ui ui;

    /**
     * Creates a new HypeBot.
     *
     * @param filePath The file path for tasks to save and load to.
     */
    public HypeBot(String filePath) {
        ui = new Ui();
        storage = new StorageManager(filePath);
        try {
            ui.showLoadingTasks();
            tasks = new Tasklist(storage.load());
            ui.showGreeting();
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
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
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
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
