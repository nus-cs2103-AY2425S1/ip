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
        switch (action.getCommand()) {
            case EXIT:
                isRunning = false;
                return "Bye. Hope to see you again soon!";
            case LIST_TASKS:
                return tasks.getTasksAsString();
            case MARK_TASK:
                return modifyTask(action, true);
            case UNMARK_TASK:
                return modifyTask(action, false);
            case DELETE_TASK:
                return deleteTask(action);
            case ADD_TODO_TASK:
            case ADD_EVENT_TASK:
            case ADD_DEADLINE_TASK:
                return addTask(action);
            case FIND_TASK:
                return findTasks(action);
            case TAG_TASK:
                tasks.addTagToTask(action.getTaskNumber(), action.getTag());
                return "OK, I've added the tag.";
            default:
                return null;
        }
    }

    private String modifyTask(Action action, boolean markAsDone) throws FredException {
        Task task = markAsDone ? tasks.markTaskAsDone(action.getTaskNumber()) : tasks.markTaskAsNotDone(action.getTaskNumber());
        String status = markAsDone ? "done" : "not done yet";
        return String.format("OK, I've marked this task as %s:\n   %s", status, task);
    }

    private String deleteTask(Action action) throws FredException {
        Task task = tasks.deleteFromTaskList(action.getTaskNumber());
        storage.deleteFromDataFile(action.getTaskNumber());
        return String.format("Noted. I've removed this task:\n   %s", task);
    }

    private String addTask(Action action) throws FredException {
        Task task = tasks.createTask(action.getTaskType(), action.getTaskDetails());
        tasks.addToTaskList(task);
        storage.appendToDataFile(task);
        return String.format("Got it. I've added this task:\n   %s\nNow you have %d tasks in the list.",
                task, tasks.getTaskListSize());
    }

    private String findTasks(Action action) {
        String tasksWithKeyword = tasks.findTasksInTaskList(action.getKeyword());
        return "Here are the matching tasks in your list:\n" + tasksWithKeyword;
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

