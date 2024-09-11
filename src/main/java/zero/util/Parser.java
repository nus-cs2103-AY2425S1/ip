package zero.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zero.exception.ZeroException;
import zero.task.Deadline;
import zero.task.Event;
import zero.task.Task;
import zero.task.TaskList;
import zero.task.Todo;
import zero.ui.Ui;

/**
 * The {@code Parser} class is responsible for parsing user commands and executing the corresponding operations
 * on the task list. It interprets commands like adding tasks, marking tasks as done, deleting tasks, and more.
 */
public class Parser {

    /**
     * Parses the full command entered by the user and executes the corresponding operation.
     *
     * @param fullCommand The complete command entered by the user.
     * @param tasks The task list to operate on.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException if the command is not recognised
     */
    public static String parseCommand(String fullCommand, TaskList tasks, Ui ui) {
        assert fullCommand != null : "Command should not be null";
        assert !fullCommand.trim().isEmpty() : "Command should not be empty";

        try {
            String commandWord = fullCommand.split(" ")[0];
            switch (commandWord) {
            case "bye":
                return ui.showGoodbye();
            case "list":
                return ui.listTasks(tasks);
            case "delete":
                return handleDelete(tasks, fullCommand, ui);
            case "mark":
                return handleMark(tasks, fullCommand, ui);
            case "unmark":
                return handleUnmark(tasks, fullCommand, ui);
            case "todo":
                return handleTodo(tasks, fullCommand, ui);
            case "deadline":
                return handleDeadline(tasks, fullCommand, ui);
            case "event":
                return handleEvent(tasks, fullCommand, ui);
            case "find":
                return handleFind(tasks, fullCommand, ui);
            default:
                throw new ZeroException("分かりません");
            }
        } catch (ZeroException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the deletion of a task based on the user's input.
     *
     * @param tasks The task list to operate on.
     * @param input The full command entered by the user.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException If the task number is invalid.
     */
    private static String handleDelete(TaskList tasks, String input, Ui ui) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int index = Integer.parseInt(strArr[1]) - 1;
            Task removedTask = tasks.deleteTask(index);
            return ui.showTaskDeleted(removedTask, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to delete.");
        }
    }

    /**
     * Handles marking a task as done based on the user's input.
     *
     * @param tasks The task list to operate on.
     * @param input The full command entered by the user.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException If the task number is invalid.
     */
    private static String handleMark(TaskList tasks, String input, Ui ui) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1; // convert to zero-based index
            tasks.getTask(choice).markAsDone();
            return ui.showTaskMarked(tasks.getTask(choice));
        } catch (NumberFormatException e) {
            throw new ZeroException("PLease enter a valid task number to mark.");
        }
    }

    /**
     * Handles unmarking a task based on the user's input.
     *
     * @param tasks The task list to operate on.
     * @param input The full command entered by the user.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException If the task number is invalid.
     */
    private static String handleUnmark(TaskList tasks, String input, Ui ui) throws ZeroException {
        try {
            String[] strArr = input.split(" ", 2);
            int choice = Integer.parseInt(strArr[1]) - 1; // convert to zero-based index
            tasks.getTask(choice).markAsNotDone();
            return ui.showTaskUnmarked(tasks.getTask(choice));
        } catch (NumberFormatException e) {
            throw new ZeroException("Please enter a valid task number to unmark.");
        }
    }

    /**
     * Handles adding a Todo task based on the user's input.
     *
     * @param tasks The task list to operate on.
     * @param input The full command entered by the user.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException If the description of the todo is empty.
     */
    private static String handleTodo(TaskList tasks, String input, Ui ui) throws ZeroException {
        if (input.trim().equals("todo")) {
            throw new ZeroException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim(); // extract description
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        return ui.showTaskAdded(newTodo, tasks.getSize());
    }

    /**
     * Handles adding a Deadline task based on the user's input.
     *
     * @param tasks The task list to operate on.
     * @param input The full command entered by the user.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException If the description of the deadline or the date/time is empty or invalid.
     */
    private static String handleDeadline(TaskList tasks, String input, Ui ui) throws ZeroException {
        String[] parts = input.split("/by ", 2);
        if (parts.length < 2 || parts[0].trim().equals("deadline")) {
            throw new ZeroException("The description of a deadline or the date/time cannot be empty.");
        }
        String description = parts[0].substring(9).trim(); // extract description
        String byString = parts[1].trim(); // extract deadline
        LocalDateTime by = handleDate(byString);
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        return ui.showTaskAdded(newDeadline, tasks.getSize());
    }

    /**
     * Handles adding an Event task based on the user's input.
     *
     * @param tasks The task list to operate on.
     * @param input The full command entered by the user.
     * @param ui The UI component used to display messages to the user.
     * @throws ZeroException If the description of the event or the date/time is empty or invalid.
     */
    private static String handleEvent(TaskList tasks, String input, Ui ui) throws ZeroException {
        String[] parts = input.split("/from | /to ");
        if (parts.length < 3 || parts[0].trim().equals("event")) {
            throw new ZeroException("The description of an event or the date/time cannot be empty.");
        }
        String description = parts[0].substring(6).trim(); // extract description
        String fromString = parts[1].trim(); // extract start time
        String toString = parts[2].trim(); // extract end time
        LocalDateTime from = handleDate(fromString);
        LocalDateTime to = handleDate(toString);
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        return ui.showTaskAdded(newEvent, tasks.getSize());
    }

    /**
     * Converts a string input into a {@code LocalDateTime} object.
     * If the input contains only a date, the time is set to 00:00.
     * If the input contains both date and time, it is parsed accordingly.
     *
     * @param input The date/time string input to parse.
     * @return The parsed {@code LocalDateTime} object.
     * @throws ZeroException If the date/time format is invalid.
     */
    public static LocalDateTime handleDate(String input) throws ZeroException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            if (input.length() <= 10) {
                // only date provided, default time to 00:00
                return LocalDateTime.parse(input + " 0000", dateTimeFormatter);
            } else {
                // date and time provided
                return LocalDateTime.parse(input, dateTimeFormatter);
            }
        } catch (DateTimeParseException e) {
            throw new ZeroException("Invalid date/time format. Please use 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }

    private static String handleFind(TaskList tasks, String input, Ui ui) throws ZeroException {

        try {
            String keyword = input.substring(5);
            return ui.listMatchingTasks(tasks, keyword);
        } catch (Exception e) {
            throw new ZeroException("Invalid find parameters");
        }

    }
}
