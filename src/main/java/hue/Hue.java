package hue;

import hue.command.*;
import hue.storage.Storage;
import hue.task.TaskList;
import hue.ui.ui;
import hue.parser.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The main class of the application that handles the execution of commands and manages the overall workflow.
 * The class initializes the user interface, storage, and task list, and runs the main loop of the application.
 */
public class Hue {

    private static Storage storage;
    private static TaskList tasks;
    private final hue.ui.ui ui;

    private String commandType;
    /**
     * Creates a {@code Hue} object with the specified file path for task storage.
     * Initializes the user interface and attempts to load tasks from the specified file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Hue(String filePath) {
        this.ui = new ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showError("File not found. Creating new file.");
        } catch (HueException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public Hue() {
        this.ui = new ui();
    }
    /**
     * Runs the main loop of the application. Reads user commands, parses them, and executes them.
     * Continues until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (HueException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input){
        try {
            Command command = Parser.parse(input);
            commandType = command.getClass().getSimpleName();
            return command.execute(tasks, ui, storage);  // Return the response from the executed command
        } catch (HueException | IOException e) {
            return e.getMessage();  // Return the error message as the response
        }
    }

    public String varagsAttempt (String... words) {
        return words[0]; //here
    }

    public String getCommandType() {
        return commandType;
    }


    public static void main (String[] args) {
        new Hue("data/Hue.txt").run();
    }


}
