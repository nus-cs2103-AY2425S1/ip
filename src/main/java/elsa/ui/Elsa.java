package elsa.ui;

import java.util.ArrayList;
import java.util.List;

import elsa.ElsaException;
import elsa.Storage;
import elsa.command.Command;
import elsa.task.Task;
import elsa.task.TaskList;

/**
 * Represents elsa.ui.Elsa, a chatbot.
 * @author Aaron
 */
public class Elsa {
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an Elsa instance.
     * Initialises the Ui instance and loads tasks from storage while handling any errors that occur during loading.
     * If an error occurs, an empty TaskList is initialised.
     */
    public Elsa() {
        ui = new Ui();

        try {
            Storage storage = new Storage(); // Initialisation of the storage may throw an ElsaException
            List<Task> savedTasks = storage.populateTaskList();
            tasks = new TaskList(savedTasks);
        } catch (ElsaException e) {
            ui.showError("Oops, looks like an error occurred when loading the tasks: " + e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Getter to pass the taskList to Gui so that it can save all tasks to the data file when the GUI window is closed.
     *
     * @return The list of tasks.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Starts and runs the Elsa chatbot.
     * Greets the user, reads and processes user commands in a loop until the user says goodbye.
     * Handles any errors that occur during command execution.
     */
    public void run() {
        ui.greetUser();

        boolean isGoodbye = false;
        while (!isGoodbye) {
            try {
                String originalCommand = ui.readCommand();
                Command command = Parser.parse(originalCommand);
                command.execute(tasks, ui);
                isGoodbye = command.isGoodbye();
            } catch (ElsaException e) {
                ui.showError(e.getMessage());
            }
        }

        try {
            Storage storage = new Storage(); // Initialisation of the storage may throw an ElsaException
            storage.saveTasksToDataFile(tasks);
        } catch (ElsaException e) {
            ui.showError("Oops, it appears that an error has occurred while writing to the data file:\n"
                    + e.getMessage());
        }
    }

    /**
     * Initialises a new instance of Elsa and invokes its run method.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Elsa().run();
    }

    /**
     * Processes the user input and executes the corresponding command.
     *
     * @param input The user's input as a string that represents the command to be executed.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui);
        } catch (ElsaException e) {
            return e.getMessage();
        }
    }
}
