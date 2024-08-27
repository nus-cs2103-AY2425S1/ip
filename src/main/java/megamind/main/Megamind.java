package megamind.main;

import megamind.storage.Storage;
import megamind.task.Deadline;
import megamind.task.Event;
import megamind.task.List;
import megamind.task.Todo;
import megamind.ui.Ui;
import megamind.parser.Parser;
import megamind.exception.InvalidCommandException;
import megamind.exception.TaskNotFoundException;
import megamind.exception.MissingParameterException;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Megamind {
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage();
    private static final Parser parser = new Parser();
    private static List taskList;

    public static void main(String[] args) {
        taskList = new List(storage.loadTasks());
        ui.greet();
        run();
    }

    /**
     * Runs the program.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        // Start a loop to read commands from the user
        while (true) {
            ui.showLine();
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            try {
                String action = parser.parseCommand(command);

                switch (action) {
                case "bye":
                    exit();
                    return;
                case "list":
                    ui.showMessage(taskList.toString());
                    break;
                case "help":
                    ui.showHelp();
                    break;
                case "unmark":
                    unmark(command);
                    break;
                case "mark":
                    mark(command);
                    break;
                case "todo":
                    addTodo(command);
                    break;
                case "deadline":
                    addDeadline(command);
                    break;
                case "event":
                    addEvent(command);
                    break;
                case "delete":
                    deleteTask(command);
                    break;
                case "find":
                    findTask(command);
                    break;
                default:
                    throw new InvalidCommandException("Unknown command. Use " +
                                                      "'help' for a list of " +
                                                      "commands.");
                }
            } catch (InvalidCommandException | TaskNotFoundException |
                     MissingParameterException |
                     DateTimeParseException e) {
                if (e instanceof DateTimeParseException) {
                    ui.showError("Invalid date/time format. Please use the " +
                                 "format: dd/MM/yyyy HHmm");
                } else {
                    ui.showError(e.getMessage());
                }
            }
        }
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static void findTask(String command) throws InvalidCommandException {
        String keyword = parser.parseDescription(command, "find");
        ui.showMessage(taskList.findTasks(keyword));
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        try {
            storage.saveTasks(taskList.getTasks());
        } catch (Exception e) {
            ui.showError("Error saving tasks.");
        } finally {
            ui.showExit();
        }
    }

    /**
     * Marks a task as not done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static void unmark(String command) throws InvalidCommandException,
            TaskNotFoundException {
        int index = parser.parseTaskIndex(command);
        boolean success = taskList.markTaskAsNotDone(index);
        if (!success)
            throw new TaskNotFoundException("Task number does not exist.");
        ui.showMarkTask(taskList.get(index), false);
    }


    /**
     * Marks a task as done.
     * If the task number is invalid, print an error message.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid.
     * @throws TaskNotFoundException   If the task is not found.
     */
    public static void mark(String command) throws InvalidCommandException,
            TaskNotFoundException {
        int index = parser.parseTaskIndex(command);
        boolean success = taskList.markTaskAsDone(index);
        if (!success)
            throw new TaskNotFoundException("Task number does not exist.");
        ui.showMarkTask(taskList.get(index), true);
    }

    /**
     * Adds a to do task to the list.
     *
     * @param command Command entered by the user.
     * @throws InvalidCommandException If the command is invalid (description
     *                                 is empty).
     */
    public static void addTodo(String command) throws InvalidCommandException {
        String description = parser.parseDescription(command, "todo");
        taskList.add(new Todo(description));
        ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
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
    public static void addDeadline(String command) throws MissingParameterException, InvalidCommandException {
        String[] words = parser.parseDeadline(command);
        taskList.add(new Deadline(words[0], words[1]));
        ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds an event task to the list.
     * If the event start or end time is not specified, print an error message.
     *
     * @param command Command entered by the user.
     */
    public static void addEvent(String command) throws MissingParameterException, InvalidCommandException {
        String[] words = parser.parseEvent(command);
        taskList.add(new Event(words[0], words[1], words[2]));
        ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
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
    public static void deleteTask(String command) throws InvalidCommandException, TaskNotFoundException {
        int index = parser.parseTaskIndex(command);
        boolean success = taskList.delete(index);
        if (!success) throw new TaskNotFoundException("Task number does not exist.");
        ui.showTaskDeleted(taskList.get(index));
    }
}
