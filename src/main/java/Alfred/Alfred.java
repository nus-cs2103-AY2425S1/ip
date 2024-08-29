package alfred;

import alfred.parser.Parser;
import alfred.storage.Storage;
import alfred.task.Task;
import alfred.exception.AlfredException;
import alfred.task.TaskList;
import alfred.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the main class that manages the operations and interactions within
 * the Alfred task management application.
 */
public class Alfred {
    private TaskList tasks;
    private Storage storage;

    /**
     * Initializes a new instance of <code>Alfred</code> with the specified file path
     * for saving and loading tasks. Attempts to load tasks from storage and handles
     * errors related to loading and file corruption.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public Alfred(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            Ui.showLoadingError(e);
            this.tasks = new TaskList();
        } catch (AlfredException e) {
            Ui.showCorruptedSaveError(e);
            storage.clearStorage();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Alfred application, managing user interactions and
     * executing commands until the user exits the application.
     * Commands include listing tasks, marking tasks as done/undone,
     * deleting tasks, and adding new tasks.
     */
    public void run() {
        // Greet user
        Ui.greet();

        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        while (!input.equals("bye")) {
            String command = Parser.getCommand(input);

            switch (command) {
            case "list":
                Ui.showList(tasks.getTasks());
                break;
            case "mark":
            case "unmark":
                boolean isMark = command.equals("mark");
                if (Parser.isValidCommand(input, command, tasks.getTasksCount())) {
                    int taskNumber = Parser.getTaskNumberFromInput(input);
                    tasks.updateTaskStatus(taskNumber, isMark);
                }
                break;
            case "delete":
                if (Parser.isValidCommand(input, "delete", tasks.getTasksCount())) {
                    int taskNumber = Parser.getTaskNumberFromInput(input);
                    tasks.deleteTask(taskNumber);
                }
                break;
            default:
                if (Task.isCreateTaskCommand(input)) {
                    try {
                        Task task = Task.initialise(input);
                        tasks.addTask(task);
                        Ui.showAddedTaskMessage(task, tasks.getTasksCount());
                    } catch (AlfredException e) {
                        Ui.showAlfredError(e);
                    } catch (Exception e) {
                        Ui.showUnexpectedError(e);
                    }
                } else {
                    Ui.showInvalidCommand();
                }
                break;
            }
            input = in.nextLine();
        }
        // save tasks
        storage.saveTasks(tasks.getTasks());

        Ui.farewell();
    }

    /**
     * The entry point of the Alfred application. Initializes the Alfred
     * instance with the specified file path and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Alfred("./data/Alfred.txt").run();
    }
}
