package bing;

import java.io.IOException;
import java.text.ParseException;

import bing.storage.Storage;
import bing.command.*;
import bing.task.TaskList;
import bing.ui.Ui;
import bing.parser.Parser;
import bing.command.InvalidCommand;

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
            assert tasks != null : "Task list can not be null.";
        } catch (IOException e) {
            ui.showError("Failed to load tasks.");
            tasks = new TaskList();
        }
    }

    /**
     * Starts the task management system and processes user commands
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = parser.parse(fullCommand);
            assert command != null : "Parsed command should not be null.";
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public String byeResponse(String input) throws ParseException {
        if (input.equals("bye")) {
            assert tasks != null : "Task list can not be null.";
            assert storage != null : "Storage can not be null.";
            try {
                storage.save(tasks.getTasks());
            } catch (IOException e) {
                return ui.showError("An error occurred while saving the data file: " + e.getMessage());
            }
            return "Bye!" + "\n" + "Have a good day !";
        }
        try {
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }


    /**
     * Main entry point for the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) throws IOException{
        new Bing("./data/tasks.txt").run();
    }
}
