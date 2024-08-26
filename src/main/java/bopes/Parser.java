package bopes;

import bopes.exception.BopesException;
import bopes.task.Deadline;
import bopes.task.Event;
import bopes.task.Task;
import bopes.task.TaskList;
import bopes.task.ToDo;

/**
 * The Parser class is responsible for interpreting user commands and interacting
 * with the TaskList, Ui, and Storage classes to perform the requested operations.
 * It handles commands such as adding, deleting, marking, and unmarking tasks, as well
 * as parsing task data from storage.
 */
public class Parser {

    /**
     * Parses the user's full command and executes the corresponding operation on the task list.
     *
     * @param fullCommand the full command string entered by the user
     * @param tasks       the TaskList object containing the current list of tasks
     * @param ui          the Ui object for interacting with the user
     * @param storage     the Storage object for saving and loading tasks
     * @throws BopesException if the command is invalid or an error occurs during execution
     */
    public static void parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        String[] commandWords = fullCommand.split(" ", 2);
        String commandType = commandWords[0];

        switch (commandType) {
            case "find":
                handleFindCommand(commandWords[1], tasks, ui);
                break;
            case "list":
                ui.showTasks(tasks);
                break;
            case "mark":
                handleMarkCommand(commandWords[1], tasks, ui, storage);
                break;
            case "unmark":
                handleUnmarkCommand(commandWords[1], tasks, ui, storage);
                break;
            case "delete":
                handleDeleteCommand(commandWords[1], tasks, ui, storage);
                break;
            default:
                handleAddTaskCommand(fullCommand, tasks, ui, storage);
                break;
        }
    }

    /**
     * Handles the "mark" command, marking the specified task as done.
     *
     * @param input    the task index as a string
     * @param tasks    the TaskList object containing the current list of tasks
     * @param ui       the Ui object for interacting with the user
     * @param storage  the Storage object for saving the updated task list
     * @throws BopesException if the input is not a valid number or if the index is out of range
     */
    private static void handleMarkCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.markTask(index);
            ui.showMarkedTask(task);
            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    /**
     * Handles the "unmark" command, marking the specified task as not done.
     *
     * @param input    the task index as a string
     * @param tasks    the TaskList object containing the current list of tasks
     * @param ui       the Ui object for interacting with the user
     * @param storage  the Storage object for saving the updated task list
     * @throws BopesException if the input is not a valid number or if the index is out of range
     */
    private static void handleUnmarkCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.unmarkTask(index);
            ui.showUnmarkedTask(task);
            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    /**
     * Handles the "delete" command, deleting the specified task from the list.
     *
     * @param input    the task index as a string
     * @param tasks    the TaskList object containing the current list of tasks
     * @param ui       the Ui object for interacting with the user
     * @param storage  the Storage object for saving the updated task list
     * @throws BopesException if the input is not a valid number or if the index is out of range
     */
    private static void handleDeleteCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.getTasks().get(index);
            tasks.deleteTask(index);
            ui.showDeletedTask(task, tasks.getTasks().size());
            storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    /**
     * Handles the addition of a new task based on the user's input command.
     * The task can be of type ToDo, Deadline, or Event.
     *
     * @param input    the full command string entered by the user
     * @param tasks    the TaskList object containing the current list of tasks
     * @param ui       the Ui object for interacting with the user
     * @param storage  the Storage object for saving the updated task list
     * @throws BopesException if the command format is invalid or if an error occurs during task creation
     */
    private static void handleAddTaskCommand(String input, TaskList tasks, Ui ui, Storage storage) throws BopesException {
        Task newTask = null;
        try {
            if (input.startsWith("todo ")) {
                newTask = new ToDo(input.substring(5), false);
            } else if (input.startsWith("deadline ")) {
                String[] temp = input.substring(9).split(" /by ");
                if (temp.length == 2) {
                    newTask = new Deadline(temp[0], temp[1], false);
                } else {
                    throw BopesException.invalidDeadlineFormat();
                }
            } else if (input.startsWith("event ")) {
                String[] temp = input.substring(6).split(" /from | /to ");
                if (temp.length == 3) {
                    newTask = new Event(temp[0], temp[1], temp[2], false);
                } else {
                    throw BopesException.invalidEventFormat();
                }
            } else {
                throw BopesException.unknownCommand();
            }
            tasks.addTask(newTask);
            ui.showAddedTask(newTask, tasks.getTasks().size());
            storage.saveTasks(tasks);
        } catch (IllegalArgumentException e) {
            throw new BopesException(e.getMessage());
        }
    }

    /**
     * Parses a task from a string representation stored in a file.
     * This method is used to reconstruct tasks when loading data from storage.
     *
     * @param taskData the string representation of the task from the file
     * @return the reconstructed Task object
     * @throws BopesException if the task data is corrupted or if the task type is unknown
     */
    public static Task parseTask(String taskData) throws BopesException {
        String[] data = taskData.split(" \\| ");
        if (data.length < 3) {
            throw new BopesException("Corrupted data: Insufficient task data in file.");
        }
        String taskType = data[0];
        boolean isDone = data[1].equals("1");  // Parse the done status
        String description = data[2];

        switch (taskType) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            return new Deadline(description, data[3], isDone);
        case "E":
            return new Event(description, data[3], data[4], isDone);
        default:
            throw new BopesException("Error: Unknown task type in file.");
        }
    }

    private static void handleFindCommand(String keyword, TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(matchingTasks);
    }
}
