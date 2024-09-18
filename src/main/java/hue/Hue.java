package hue;

import hue.command.*;
import hue.storage.Storage;
import hue.task.TaskList;
import hue.ui.Ui;
import hue.parser.Parser;
import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;


/**
 * The main class of the application that handles the execution of commands and manages the overall workflow.
 * The class initializes the user interface, storage, and task list, and runs the main loop of the application.
 */
public class Hue {

    private static Storage storage;
    private static TaskList tasks;
    private final Ui ui;

    private String commandType;
    /**
     * Creates a {@code Hue} object with the specified file path for task storage.
     * Initializes the user interface and attempts to load tasks from the specified file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Hue(String filePath) {
        createDataFile(filePath);
        this.ui = new Ui();
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
    /**
     * Creates a new data file at the specified file path if it does not already exist.
     * <p>
     * This method checks whether a file exists at the given {@code filePath}. If the file does not
     * exist, it creates the necessary directories and then create the file. If the file already
     * exists, no action is taken. 
     * </p>
     * <p>
     * This method also handles any {@code IOException} that may occur during file creation, printing
     * the stack trace for debugging purposes.
     * </p>
     *
     * @param filePath The path where the file should be created. This can include directory names,
     *                 which will be created if they do not exist.
     * @throws IOException If an I/O error occurs while creating the file or directories.
     */
  public static void createDataFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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


    public String getCommandType() {
        return commandType;
    }


    public static void main (String[] args) {
        new Hue("data/Hue.txt").run();
    }


}
