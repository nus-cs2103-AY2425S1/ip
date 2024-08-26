import exceptions.JarException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * The main class that runs the Jar bot application.
 * This class initializes the necessary components and controls the flow of the program.
 */
public class Jar {
    //class fields for parser and UI
    private Parser parser;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Jar bot with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Jar(String filePath) {
        parser = new Parser();
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage);
        } catch (IOException | JarException e) {
            ui.showResponse("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Jar bot, handling user input and processing commands in a loop until the user exits.
     */
    public void runBot() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            ui.showLine();
            try {
                isRunning = parser.handleCommand(userInput, taskList, ui);
            } catch (JarException e) {
                ui.showResponse(e.getMessage());
            }
            ui.showLine();
        }
        saveTasksBeforeExit();
    }

    /**
     * Saves the tasks to storage before exiting the application.
     * Displays a success message if tasks are saved successfully, or an error message if saving fails.
     */
    private void saveTasksBeforeExit() {
        try {
            storage.save(taskList.getTasks());
            ui.showResponse("Tasks saved successfully.");
        } catch (IOException e) {
            ui.showResponse("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * The main entry point of the Jar bot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Jar jar = new Jar("./data/jar.txt");
        jar.runBot();
    }
}
