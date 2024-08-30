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
 * The {@code Parser} class is responsible for parsing user input commands
 * and executing the appropriate actions based on the command keyword.
 * It interacts with the {@code TaskList}, {@code Storage}, and {@code Ui} classes
 * to perform various operations on tasks.
 */
public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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
            case "bye" -> "Bye. Hope to see you again soon!";
            case "list" -> taskList.listTasks();
            case "mark" -> handleMark(taskList, storage, words);
            case "unmark" -> handleUnmark(taskList, storage, words);
            case "todo" -> handleTodo(taskList, storage, words);
            case "deadline" -> handleDeadline(taskList, storage, words);
            case "event" -> handleEvent(taskList, storage, words);
            case "delete" -> handleDelete(taskList, storage, words);
            case "find" -> handleFind(taskList, words);
            default -> throw new RubyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words.
     * @return A string response indicating the task is marked as done.
     * @throws RubyException If the task number is not specified or invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleMark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {

        if (words.length < 2) {
            throw new RubyException("Please specify the task number to mark.");
        }

        int markIndex = Integer.parseInt(words[1]) - 1;
        taskList.getTask(markIndex).markAsDone();
        storage.save(taskList.getTasks());
        return "Nice! I've marked this task as done:\n     " + taskList.getTask(markIndex);
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words.
     * @return A string response indicating the task is marked as not done.
     * @throws RubyException If the task number is not specified or invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleUnmark(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {

        if (words.length < 2) {
            throw new RubyException("Please specify the task number to unmark.");
        }

        int unmarkIndex = Integer.parseInt(words[1]) - 1;
        taskList.getTask(unmarkIndex).markAsNotDone();
        storage.save(taskList.getTasks());
        return "OK, I've marked this task as not done yet:\n     " + taskList.getTask(unmarkIndex);
    }

    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words.
     * @return A string response indicating the todo task is added.
     * @throws RubyException If the description of the todo is empty.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleTodo(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a todo cannot be empty.");
        }

        Task todo = new Todo(words[1].trim());
        taskList.addTask(todo);
        storage.save(taskList.getTasks());
        return "Got it. I've added this task:\n     " + todo + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words.
     * @return A string response indicating the deadline task is added.
     * @throws RubyException If the description or date/time of the deadline is invalid.
     */
    private String handleDeadline(TaskList taskList, Storage storage, String[] words) throws RubyException {

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] deadlineParts = words[1].split(" /by ");

        if (deadlineParts.length < 2) {
            throw new RubyException("OOPS!!! The description of a deadline must include a date/time.");
        }

        try {
            LocalDateTime deadlineDate = LocalDateTime.parse(deadlineParts[1].trim(), DATE_FORMAT);
            Task deadline = new Deadline(deadlineParts[0].trim(), deadlineDate);
            taskList.addTask(deadline);
            storage.save(taskList.getTasks());
            return "Got it. I've added this task:\n     " + deadline + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (DateTimeParseException | IOException e) {
            throw new RubyException("OOPS!!! The date/time format should be 'yyyy-MM-dd HH:mm'.");
        }
    }

    /**
     * Handles the "event" command to add a new event task.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words.
     * @return A string response indicating the event task is added.
     * @throws RubyException If the description, start time, or end time of the event is invalid.
     */
    private String handleEvent(TaskList taskList, Storage storage, String[] words) throws RubyException {

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new RubyException("OOPS!!! The description of an event cannot be empty.");
        }

        String[] eventParts = words[1].split(" /from ");

        if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
            throw new RubyException("OOPS!!! The description of an event must include start and end times.");
        }

        String[] times = eventParts[1].split(" /to ");

        try {
            LocalDateTime startTime = LocalDateTime.parse(times[0].trim(), DATE_FORMAT);
            LocalDateTime endTime = LocalDateTime.parse(times[1].trim(), DATE_FORMAT);

            Task event = new Event(eventParts[0].trim(), startTime, endTime);
            taskList.addTask(event);
            storage.save(taskList.getTasks());
            return "Got it. I've added this task:\n     " + event + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (DateTimeParseException | IOException e) {
            throw new RubyException("OOPS!!! The date/time format should be 'yyyy-MM-dd HH:mm'.");
        }
    }

    /**
     * Handles the "delete" command to remove a task from the list.
     *
     * @param taskList The current list of tasks.
     * @param storage  The storage object to save the updated tasks.
     * @param words    The command split into words.
     * @return A string response indicating the task is deleted.
     * @throws RubyException If the task number is not specified or invalid.
     * @throws IOException   If an I/O error occurs while saving tasks.
     */
    private String handleDelete(TaskList taskList, Storage storage, String[] words) throws RubyException, IOException {

        if (words.length < 2) {
            throw new RubyException("Please specify the task number to delete.");
        }

        int deleteIndex = Integer.parseInt(words[1]) - 1;
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        storage.save(taskList.getTasks());
        return "Noted. I've removed this task:\n     " + deletedTask + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the 'find' command by searching for tasks that contain the specified keyword in their description.
     *
     * @param taskList The list of tasks to search within.
     * @param words The keywords to search for in task descriptions.
     * @return A string containing the matching tasks found in the task list.
     */
    private String handleFind(TaskList taskList, String[] words) throws RubyException {
        if (words.length < 2) {
            throw new RubyException("Please specify a keyword to find tasks.");
        }
        String keyword = words[1];
        return taskList.findTasks(keyword);
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

