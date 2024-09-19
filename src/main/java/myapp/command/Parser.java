package myapp.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import myapp.storage.Storage;
import myapp.task.*;
import myapp.ui.Ui;
import myapp.exception.RubyException;

/**
 * The {@code Parser} class parses user input commands and executes the appropriate actions.
 * It interacts with the {@code TaskList}, {@code Storage}, and {@code Ui} classes.
 */
public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String EMPTY_DESCRIPTION_ERROR = "OOPS!!! The description cannot be empty.";
    private static final String INVALID_DATE_FORMAT_ERROR = "OOPS!!! The date/time format should be 'yyyy-MM-dd HHmm'.";

    /**
     * Parses the given command and executes the appropriate action.
     *
     * @param command  The user input command.
     * @param taskList The current list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage object to save and load tasks.
     * @return A string response based on the command executed.
     * @throws RubyException If the command is invalid or any error occurs.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    public String parse(String command, TaskList taskList, Ui ui, Storage storage) throws RubyException, IOException {
        String[] words = command.split(" ", 2);
        String keyword = words[0];

        return switch (keyword) {
            case "bye" -> {
                try {
                    storage.save(taskList.getTasks());
                } catch (IOException e) {
                    yield "Error saving tasks before exit: " + e.getMessage();
                }
                yield "bye";
            }
            case "list" -> taskList.listTasks();
            case "mark" -> handleMark(taskList, storage, words);
            case "unmark" -> handleUnmark(taskList, storage, words);
            case "todo" -> handleTodo(taskList, storage, words);
            case "deadline" -> handleDeadline(taskList, storage, words);
            case "event" -> handleEvent(taskList, storage, words);
            case "delete" -> handleDelete(taskList, storage, words);
            case "find" -> handleFind(taskList, words);
            case "sort" -> handleSort(taskList, ui, storage);
            default -> throw new RubyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words, where the second word represents the task number.
     * @return A string response indicating the task is marked as done.
     * @throws RubyException If the task number is not specified or invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleMark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        validateCommand(words, 2, "Please specify the task number to mark.");
        int markIndex = parseTaskIndex(words[1]);
        taskList.getTask(markIndex).markAsDone();
        saveTasks(storage, taskList);
        return "Nice! I've marked this task as done:\n     " + taskList.getTask(markIndex);
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words, where the second word represents the task number.
     * @return A string response indicating the task is marked as not done.
     * @throws RubyException If the task number is not specified or invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleUnmark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        validateCommand(words, 2, "Please specify the task number to unmark.");
        int unmarkIndex = parseTaskIndex(words[1]);
        taskList.getTask(unmarkIndex).markAsNotDone();
        saveTasks(storage, taskList);
        return "OK, I've marked this task as not done yet:\n     " + taskList.getTask(unmarkIndex);
    }

    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words, where the second word represents the task description.
     * @return A string response indicating the todo task is added.
     * @throws RubyException If the description of the todo is empty.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleTodo(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        validateDescription(words);
        Task todo = new Todo(words[1].trim());
        taskList.addTask(todo);
        saveTasks(storage, taskList);
        return "Got it. I've added this task:\n     " + todo + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words, where the second word represents the task description and deadline.
     * @return A string response indicating the deadline task is added.
     * @throws RubyException If the description or date/time of the deadline is invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleDeadline(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        validateDescription(words);
        String[] deadlineParts = splitCommand(words[1], " /by ");
        LocalDateTime deadlineDate = parseDateTime(deadlineParts[1]);
        Task deadline = new Deadline(deadlineParts[0].trim(), deadlineDate);
        taskList.addTask(deadline);
        saveTasks(storage, taskList);
        return "Got it. I've added this task:\n     " + deadline + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "event" command to add a new event task.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words, where the second word represents the task description, start time, and end time.
     * @return A string response indicating the event task is added.
     * @throws RubyException If the description, start time, or end time of the event is invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleEvent(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        validateDescription(words);
        String[] eventParts = splitCommand(words[1], " /from ");
        String[] times = splitCommand(eventParts[1], " /to ");
        LocalDateTime startTime = parseDateTime(times[0]);
        LocalDateTime endTime = parseDateTime(times[1]);
        Task event = new Event(eventParts[0].trim(), startTime, endTime);
        taskList.addTask(event);
        saveTasks(storage, taskList);
        return "Got it. I've added this task:\n     " + event + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "delete" command to remove a task from the list.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words, where the second word represents the task number.
     * @return A string response indicating the task is deleted.
     * @throws RubyException If the task number is not specified or invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleDelete(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {
        validateCommand(words, 2, "Please specify the task number to delete.");
        int deleteIndex = parseTaskIndex(words[1]);
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        saveTasks(storage, taskList);
        return "Noted. I've removed this task:\n     " + deletedTask + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "find" command to search for tasks that contain the specified keyword in their description.
     *
     * @param taskList The list of tasks to search within.
     * @param words    The command split into words, where the second word represents the search keyword.
     * @return A string containing the matching tasks found in the task list.
     * @throws RubyException If no keyword is specified for the search.
     */
    private String handleFind(TaskList taskList, String[] words) throws RubyException {
        validateCommand(words, 2, "Please specify a keyword to find tasks.");
        String keyword = words[1];
        return taskList.findTasks(keyword);
    }

    /**
     * Handles the sorting of tasks alphabetically.
     *
     * @param taskList The task list to be sorted.
     * @param ui       The UI to show responses.
     * @param storage  The storage to save sorted tasks.
     * @return The response message after sorting.
     * @throws IOException If an I/O error occurs while saving.
     */
    private String handleSort(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.sortTasksAlphabetically();
        storage.save(taskList.getTasks());
        return ui.showSortedTasks(taskList.getTasks());
    }

    /**
     * Validates that the command array has the expected number of elements.
     *
     * @param words        The command split into words.
     * @param expectedLength The expected length of the command array.
     * @param errorMessage The error message to throw if validation fails.
     * @throws RubyException If the command array does not meet the expected length.
     */
    private void validateCommand(String[] words, int expectedLength, String errorMessage) throws RubyException {
        if (words.length < expectedLength) {
            throw new RubyException(errorMessage);
        }
    }

    /**
     * Validates that the description of a task is not empty.
     *
     * @param words The command split into words, where the second word represents the task description.
     * @throws RubyException If the description of the task is empty.
     */
    private void validateDescription(String[] words) throws RubyException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException(EMPTY_DESCRIPTION_ERROR);
        }
    }

    /**
     * Parses the task index from the command words.
     *
     * @param taskIndexStr The task index as a string.
     * @return The task index as an integer.
     * @throws RubyException If the task index is invalid.
     */
    private int parseTaskIndex(String taskIndexStr) throws RubyException {
        try {
            return Integer.parseInt(taskIndexStr) - 1;
        } catch (NumberFormatException e) {
            throw new RubyException("OOPS!!! The task number is invalid.");
        }
    }

    /**
     * Parses a date/time string into a {@code LocalDateTime} object.
     *
     * @param dateTimeStr The date/time string to parse.
     * @return The parsed {@code LocalDateTime} object.
     * @throws RubyException If the date/time format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws RubyException {
        try {
            return LocalDateTime.parse(dateTimeStr.trim(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new RubyException(INVALID_DATE_FORMAT_ERROR);
        }
    }

    /**
     * Splits a command string using the specified delimiter and checks the validity of the split result.
     *
     * @param command   The command string to split.
     * @param delimiter The delimiter to use for splitting the command.
     * @return An array of strings resulting from the split operation.
     * @throws RubyException If the split result does not meet the expected format.
     */
    private String[] splitCommand(String command, String delimiter) throws RubyException {
        String[] parts = command.split(delimiter);
        if (parts.length < 2) {
            throw new RubyException("OOPS!!! The command format is incorrect.");
        }
        return parts;
    }

    /**
     * Saves the current list of tasks using the specified storage object.
     *
     * @param storage  The storage object to save the tasks.
     * @param taskList The current list of tasks to save.
     * @throws IOException If an I/O error occurs while saving tasks.
     */
    private void saveTasks(Storage storage, TaskList taskList) throws IOException {
        storage.save(taskList.getTasks());
    }

    /**
     * Checks if the command is an exit command.
     *
     * @param command The user input command.
     * @return {@code true} if the command is "bye", otherwise {@code false}.
     */
    public boolean isExit(String command) {
        return command.equals("bye");
    }
}
