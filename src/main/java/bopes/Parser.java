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
     * @param storage     the Storage object for saving and loading tasks
     * @return the response after executing the command
     * @throws BopesException if the command is invalid or an error occurs during execution
     */
    public static String parse(String fullCommand, TaskList tasks, Storage storage) throws BopesException {
        String[] commandWords = fullCommand.split(" ", 2);
        assert commandWords.length > 0 : "Command should not be empty.";
        String commandType = commandWords[0];

        switch (commandType) {
        case "bye":
            return handleByeCommand();  // Handle the "bye" command to display message and exit
        case "list":
            return tasks.toString(); // The `list` command doesn't need any arguments
        case "find":
            if (commandWords.length > 1) {
                return handleFindCommand(commandWords[1], tasks);
            } else {
                throw new BopesException("The 'find' command requires a keyword.");
            }
        case "mark":
            if (commandWords.length > 1) {
                return handleMarkCommand(commandWords[1], tasks, storage);
            } else {
                throw new BopesException("The 'mark' command requires a task index.");
            }
        case "unmark":
            if (commandWords.length > 1) {
                return handleUnmarkCommand(commandWords[1], tasks, storage);
            } else {
                throw new BopesException("The 'unmark' command requires a task index.");
            }
        case "delete":
            if (commandWords.length > 1) {
                return handleDeleteCommand(commandWords[1], tasks, storage);
            } else {
                throw new BopesException("The 'delete' command requires a task index.");
            }
        default:
            return handleAddTaskCommand(fullCommand, tasks, storage);
        }
    }

    private static String handleByeCommand() {
        String goodbyeMessage = "Goodbye! The program will exit in 5 seconds...";
        new Thread(() -> {
            try {
                // Pause for 5 seconds
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }).start();
    
    return goodbyeMessage;
    }

    private static String handleMarkCommand(String input, TaskList tasks, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.markTask(index);
            storage.saveTasks(tasks);
            return "Marked task: " + task.toString();
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    private static String handleUnmarkCommand(String input, TaskList tasks, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.unmarkTask(index);
            storage.saveTasks(tasks);
            return "Unmarked task: " + task.toString();
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    private static String handleDeleteCommand(String input, TaskList tasks, Storage storage) throws BopesException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task task = tasks.getTasks().get(index);
            tasks.deleteTask(index);
            storage.saveTasks(tasks);
            return "Deleted task: " + task.toString();
        } catch (NumberFormatException e) {
            throw BopesException.invalidNumberFormat();
        }
    }

    private static String handleAddTaskCommand(String input, TaskList tasks, Storage storage) throws BopesException {
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
            storage.saveTasks(tasks);
            return "Added task: " + newTask.toString();
        } catch (IllegalArgumentException e) {
            throw new BopesException(e.getMessage());
        }
    }

    private static String handleFindCommand(String keyword, TaskList tasks) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        return matchingTasks.toString();  // Assuming `toString` returns the matching tasks
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
        boolean isDone = data[1].trim().equals("1");  // Parse the done status
        String description = data[2].trim();

        switch (taskType) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                if (data.length < 4) {
                    throw new BopesException("Corrupted data: Missing deadline information.");
                }
                String by = data[3].trim();
                return new Deadline(description, by, isDone);
            case "E":
                if (data.length < 5) {
                    throw new BopesException("Corrupted data: Missing event start/end information.");
                }
                String from = data[3].trim();
                String to = data[4].trim();
                return new Event(description, from, to, isDone);
            default:
                throw new BopesException("Error: Unknown task type in file.");
        }
    }
}
