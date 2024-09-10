package hypebot;

import hypebot.command.Command;
import hypebot.parser.Parser;
import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The chatbot which the user interacts with.
 * @author Youngseo Park (@youngseopark05)
 */
public class HypeBot {
    private StorageManager storage;
    private Tasklist tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        try {
            new HypeBot("./src/main/data/tasks.txt").run();
        } catch (Exception e) {
            new HypeBot("../../src/main/data/tasks.txt").run();
        }
    }
}
