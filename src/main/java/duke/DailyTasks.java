package duke;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The main class for the DailyTasks application, which acts as a task manager.
 * It handles initializing the application, starting the task processing loop, and saving the state before exiting.
 */
public class DailyTasks {

    /** The list of tasks managed by the application. */
    private final TaskList taskList;

    /** The user interface component for interacting with the user. */
    private final Ui ui;

    /** The storage component for saving and loading tasks from a file. */
    private final Storage storage;

    /**
     * Constructs a new DailyTasks application and loads the tasks from storage.
     * If there is an issue loading the tasks, an error message is printed.
     */
    public DailyTasks() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            List<Task> tasks = storage.loadStateFileToTasksList();
            this.taskList.setTasks(tasks);
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    /**
     * The main method that starts the DailyTasks application.
     * It initializes the application and starts the task input loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DailyTasks dailyTasks = new DailyTasks();

        try {
            dailyTasks.start();
        } finally {
            dailyTasks.end();
        }
    }

    /**
     * Starts the main input loop for the DailyTasks application.
     * This method listens for user input and executes the corresponding commands until the user exits.
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        this.ui.showWelcome();

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                this.ui.showGoodbye();
                break;
            }

            Command command = Parser.parseUserInput(userInput);
            command.execute(this.taskList, this.ui, this.storage);
        }
    }

    /**
     * Saves the current task list to a file and prints a message indicating success or failure.
     * This method is called before the application terminates.
     */
    private void end() {
        try {
            Storage.saveTasksListToStateFile(this.taskList.getTasks());
            System.out.println("saved");
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }
}
