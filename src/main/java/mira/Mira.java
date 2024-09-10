package mira;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import mira.command.Command;

/**
 * Mira is a simple chatbot that echoes user commands and exits when the user types "bye".
 * It interacts with the user via the Ui class, which handles input and output operations.
 */
public class Mira {
    private final Storage storage; // storage for saving, loading tasks
    private TaskList tasks; // Manages tasks
    private ExpenseList expenses;

    /**
     * Constructs a new {@code Mira} instance, initializing the storage, and task list.
     * <p>
     * This constructor attempts to load the tasks from the specified file path. If the tasks
     * cannot be loaded due to an error, it displays an appropriate message to the user and
     * initializes an empty task list.
     * </p>
     *
     * @param filePath The path to the file where tasks will be stored and loaded from.
     *                 If the file does not exist, it will be created.
     */
    public Mira(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (MiraException | SecurityException | IOException e) {
            this.tasks = new TaskList();
        }
        this.expenses = new ExpenseList();
    }

    /**
     * Reads user commands and responding until the user types "bye".
     */
    public String getResponse(String userInput) {
        Command command = null;
        try {
            assert userInput != null : "user input should not be null";
            command = Parser.parse(userInput);
            assert tasks != null : "tasks should not be null";
            command.setTaskList(tasks, expenses);
            String commandResult = command.execute();
            if (command instanceof Savable savable) { // if command is Savable
                savable.save(storage);
            }
            return commandResult;
        } catch (MiraException e) {
            return e.getMessage();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please provide a valid task number.";
        } catch (DateTimeParseException e) {
            return "Please provide a valid task date: 'd/M/yyyy HHmm'.";
        } catch (IOException e) {
            return "File path for storing of tasks is unusable.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
