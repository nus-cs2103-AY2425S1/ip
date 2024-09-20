package fred;

import fred.Exceptions.FredException;
import fred.Tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Fred class serves as the main class for a task management application.
 * It initializes essential components such as Storage, TaskList, Ui, and Parser,
 * and provides methods to run the application, execute user actions, and handle
 * tasks like adding, marking, or deleting tasks.
 */
public class Fred {
    Storage storage;
    TaskList tasks;
    Ui ui;
    Parser parser;
    boolean isRunning;

    /**
     * Constructs a new Fred object and initializes the Storage, TaskList, Ui, and Parser components.
     */
    public Fred() {
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
        isRunning = true;
    }

    /**
     * Runs the Fred application. This method loads tasks from the storage, greets the user,
     * and enters a loop to process user inputs and execute corresponding actions.
     */
    void run() {
        storage.getDataFile();
        ArrayList<Task> tasksFromDataFile = storage.getTasksFromDataFile();
        tasks.loadTasksFromDataFile(tasksFromDataFile);
    }

    /**
     * The main method to start the Fred application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Fred().run();
    }

    /**
     * Exits the Fred application by terminating the program.
     */
    void exit() {
        System.exit(0);
    }

    /**
     * Executes the user action based on the parsed input. The method supports
     * various actions such as adding a task, marking it as done or not done,
     * deleting a task, and printing the task list.
     *
     * @param action A String array containing the action and its parameters.
     * @throws FredException If the action is invalid or cannot be executed.
     */
    String executeAction(Action action) throws FredException {
        String message;
        Task task;
        switch (action.getCommand()) {
        case EXIT:
            message = "Bye. Hope to see you again soon!";
            isRunning = false;
            break;
        case LIST_TASKS:
            message = tasks.getTasksAsString();
            break;
        case MARK_TASK:
            task = tasks.markTaskAsDone(action.getTaskNumber());
            message = String.format("Nice! I've marked this task as done:\n" +
                    "   %s", task);
            break;
        case UNMARK_TASK:
            task = tasks.markTaskAsNotDone(action.getTaskNumber());
            message = String.format("OK, I've marked this task as not done yet:\n" +
                    "   %s", task);
            break;
        case DELETE_TASK:
            task = tasks.deleteFromTaskList(action.getTaskNumber());
            storage.deleteFromDataFile(action.getTaskNumber());
            message = String.format("Noted. I've removed this task:\n" +
                    "   %s", task);
            break;
        case ADD_TODO_TASK:
        case ADD_EVENT_TASK:
        case ADD_DEADLINE_TASK:
            task = tasks.createTask(action.getTaskType(), action.getTaskDetails());
            tasks.addToTaskList(task);
            storage.appendToDataFile(task);
            message = String.format("Got it. I've added this task:\n" +
                    "   %s\n" +
                    "Now you have %d tasks in the list.", task, tasks.getTaskListSize());
            break;
        case FIND_TASK:
            String tasksWithKeyword = tasks.findTasksInTaskList(action.getKeyword());
            message = "Here are the matching tasks in your list:\n" + tasksWithKeyword;
            break;
        case TAG_TASK:
            task  = tasks.addTagToTask(action.getTaskNumber(), action.getTag());
            message = String.format("OK, I've added the tag to this task:\n" +
                    "   %s\n", task);
            break;
        default:
            return null;
        }
        return message;
    }

    String getResponse(String input) {
        try {
            Action action = parser.parseInput(input);
            Command command = action.getCommand();
            List<Command> commandList = Arrays.asList(Command.values());
            assert commandList.contains(command) : "Command not recognized";
            return executeAction(action);
        } catch (FredException e) {
            return e.getMessage();
        }
    }

    boolean getIsRunning() {
        return isRunning;
    }
}

