package bing;

import java.io.IOException;
import bing.storage.Storage;
import bing.command.*;
import bing.task.TaskList;
import bing.ui.Ui;
import bing.parser.Parser;

/**
 * Bing is the main class that initializes and runs the task management system.
 * It handles interactions between the user, storage, tasks, and parser.
 */
public class Bing {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    /**
     * Initializes a new Bing instance and loads tasks from the specified file.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Bing(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Failed to load tasks.");
            tasks = new TaskList();
        }
    }

    /**
     * Starts the task management system and processes user commands
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    /**
     * Main entry point for the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Bing("data/tasks.txt").run();
    }
}
