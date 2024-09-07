package megamind.main;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import megamind.exception.InvalidCommandException;
import megamind.exception.MissingParameterException;
import megamind.exception.TaskNotFoundException;
import megamind.parser.Parser;
import megamind.storage.Storage;
import megamind.task.Deadline;
import megamind.task.Event;
import megamind.task.List;
import megamind.task.Task;
import megamind.task.Todo;
import megamind.ui.Ui;

/**
 * The `Megamind` class is the main entry point for the application.
 * It initializes the necessary components and handles user commands.
 * The class provides methods to run the program, process commands, and manage tasks.
 */
public class Megamind {
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage();
    private static List taskList;

    /**
     * Constructs a new `Megamind` instance and initializes the task list.
     */
    public Megamind() {
        taskList = new List(storage.loadTasks());
        assert taskList.getTasks() instanceof ArrayList<Task> : "taskList.getTasks() should be an instance of ArrayList<Task>";
    }

    /**
     * Handles the user command and returns the result as a string
     *
     * @param command Command entered by the user
     * @return String Returns the string that corresponds to the result
     */
    public static String handleCommand(String command) {
        assert command != null && !command.isEmpty() : "Command should not be null or empty";
        try {
            String action = parser.parseCommand(command);

            return switch (action) {
            case "bye" -> exit();
            case "list" -> taskList.toString();
            case "help" -> ui.showHelp();
            case "unmark" -> unmark(command);
            case "mark" -> mark(command);
            case "todo" -> addTodo(command);
            case "deadline" -> addDeadline(command);
            case "event" -> addEvent(command);
            case "delete" -> deleteTask(command);
            case "find" -> findTask(command);
            default -> throw new InvalidCommandException("Unknown command. Use "
                                                             + "'help' for a list of "
                                                             + "commands.");
            };
        } catch (InvalidCommandException | TaskNotFoundException
                 | MissingParameterException
                 | DateTimeParseException e) {
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static String findTask(String command) throws InvalidCommandException {
        String keyword = parser.parseDescription(command, "find");
        return taskList.findTasks(keyword);
    }

    /**
     * Exits the program.
     */
    public static String exit() {
        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            return "Error saving tasks.";
        }

        return ui.showExit();

    }

    /**
     * Marks a task as not done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static String unmark(String command) throws InvalidCommandException,
            TaskNotFoundException {
        int index = parser.parseTaskIndex(command);
        boolean success = taskList.markTaskAsNotDone(index);
        if (!success) {
            throw new TaskNotFoundException("Task number does not exist.");
        }
        return ui.showMarkTask(taskList.get(index), false);
    }


    /**
     * Marks a task as done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static String mark(String command) throws InvalidCommandException,
            TaskNotFoundException {
        int index = parser.parseTaskIndex(command);
        boolean success = taskList.markTaskAsDone(index);
        if (!success) {
            throw new TaskNotFoundException("Task number does not exist.");
        }
        return ui.showMarkTask(taskList.get(index), true);
    }

    /**
     * Adds a to do task to the list.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid (description
     *                                 is empty).
     */
    public static String addTodo(String command) throws InvalidCommandException {
        String description = parser.parseDescription(command, "todo");
        taskList.add(new Todo(description));
        return ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds a deadline task to the list.
     * If the deadline is not specified, print an error message.
     *
     * @param command Command entered by the user.
     * @throws MissingParameterException If the deadline is not specified.
     * @throws InvalidCommandException   If the command is invalid
     *                                   (description and/or deadline is empty).
     */
    public static String addDeadline(String command) throws MissingParameterException, InvalidCommandException {
        String[] words = parser.parseDeadline(command);
        taskList.add(new Deadline(words[0], words[1]));
        return ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds an event task to the list.
     * If the event start or end time is not specified, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static String addEvent(String command) throws MissingParameterException, InvalidCommandException {
        String[] words = parser.parseEvent(command);
        taskList.add(new Event(words[0], words[1], words[2]));
        return ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
    }

    /**
     * Deletes a task from the list.
     * If the task number is not specified, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid (task number
     *                                 is missing).
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static String deleteTask(String command) throws InvalidCommandException, TaskNotFoundException {
        int index = parser.parseTaskIndex(command);
        boolean success = taskList.delete(index);
        if (!success) {
            throw new TaskNotFoundException("Task number does not exist.");
        }
        return ui.showTaskDeleted(taskList.get(index));
    }

    public String greet() {
        return ui.greet();
    }
}
