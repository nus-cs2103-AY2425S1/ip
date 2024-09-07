package jar;

import exceptions.JarException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * The main class that runs the jar.Jar bot application.
 * This class initializes the necessary components and controls the flow of the program.
 */
public class Jar {
    //class fields for parser and UI
    private Parser parser;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a jar.Jar bot with the specified file path for storage.
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
     * Processes the user input and returns the response from the Jar bot.
     * <p>
     * This method handles the user's input command by passing it to the parser,
     * which then executes the corresponding action. If the command is "exit",
     * it saves the tasks and returns a goodbye message. If any exceptions occur
     * during processing, an error message is returned.
     * </p>
     *
     * @param input The user's input command as a string.
     * @return The response from the Jar bot as a string. If the input command is "exit",
     * the response will include the goodbye message after saving tasks.
     * If an error occurs during processing, the response will contain an error message.
     */
    public String getResponse(String input) {
        // Assert that the input command is not null or empty
        assert input != null && !input.trim().isEmpty() : "Input command cannot be null or empty";

        try {
            String output = parser.handleCommand(input, taskList, ui);
            if (output.equalsIgnoreCase("exit")) {
                saveTasksBeforeExit();
                output = ui.showGoodbye();
            }
            return output;
        } catch (JarException e) {
            return "Error: " + e.getMessage();
        }
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
}
