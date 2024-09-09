package yapper;

import java.io.IOException;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import yapper.exception.EmptyDescriptionException;
import yapper.exception.InvalidTaskNumberException;
import yapper.exception.UnknownCommandException;
import yapper.exception.YapperException;
import yapper.storage.Storage;
import yapper.task.Deadline;
import yapper.task.Event;
import yapper.task.Task;
import yapper.task.TaskList;
import yapper.task.Todo;
import yapper.ui.Ui;

/**
 * The Yapper class serves as the main entry point for the application.
 * It manages user input, processes commands, and interacts with the task list,
 * user interface, and storage components to provide the full functionality
 * of the Yapper task management application.
 */
public class Yapper {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a new Yapper instance that initializes the task list and storage
     * with the provided file path.
     *
     * @param filePath The path to the file used for storing the tasks.
     */
    public Yapper(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Main.main(args);
    }

    /**
     * Handles the "mark" command by marking a task as done.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A confirmation message that the task has been marked as done.
     * @throws YapperException If the task number is invalid or no task number is provided.
     */
    private String handleMark(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("mark");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException(taskIndex);
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        ui.printTaskMarked(task);
        return "Awesome job, Boss! I've marked this task as complete:\n  "
               + task;
    }

    /**
     * Handles the "unmark" command by marking a task as not done.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A confirmation message that the task has been marked as not done.
     * @throws YapperException If the task number is invalid or no task number is provided.
     */
    private String handleUnmark(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("unmark");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException(taskIndex);
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        return "Understood, Boss! I've marked this task as not done yet:\n  "
               + task;
    }

    /**
     * Handles the "todo" command by adding a new Todo task.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A confirmation message that the Todo task has been added.
     * @throws YapperException If the task description is empty.
     */
    private String handleTodo(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("todo");
        }
        Task task = new Todo(parts[1]);
        tasks.addTask(task);
        return "Got it, Boss! I've added this task to your list:\n  "
               + task + "\nNow you have " + tasks.getSize() + " tasks to crush!";
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A confirmation message that the Deadline task has been added.
     * @throws YapperException If the deadline description or date is missing.
     */
    private String handleDeadline(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" /by ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("deadline");
        }
        String description = parts[0].substring(9).trim();
        Task task = new Deadline(description, parts[1]);
        tasks.addTask(task);
        return "Roger that, Boss! Deadline task added:\n  "
               + task + "\nNow you have " + tasks.getSize() + " tasks on the clock.";
    }

    /**
     * Handles the "event" command by adding a new Event task.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A confirmation message that the Event task has been added.
     * @throws YapperException If the event description, start time, or end time is missing.
     */
    private String handleEvent(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" /from ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("event");
        }
        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            throw new YapperException("Boss, the event command needs both a start and end time.");
        }
        String description = parts[0].substring(6).trim();
        Task task = new Event(description, times[0], times[1]);
        tasks.addTask(task);
        return "Got it, Boss! Event added to your schedule:\n  "
               + task + "\nNow you have " + tasks.getSize() + " tasks to manage!";
    }

    /**
     * Handles the "delete" command by removing a task from the list.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A confirmation message that the task has been deleted.
     * @throws YapperException If the task number is invalid or no task number is provided.
     */
    private String handleDelete(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("delete");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new InvalidTaskNumberException(taskIndex);
        }
        Task task = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        return "Task removed, Boss! I've taken care of this:\n  "
               + task + "\nNow you have " + tasks.getSize() + " tasks left on the list.";
    }

    /**
     * Handles the "find" command by searching for tasks that match the given keyword.
     *
     * @param fullCommand The full command string entered by the user.
     * @return A list of matching tasks or a message if no tasks match the keyword.
     * @throws YapperException If no keyword is provided for the search.
     */
    private String handleFind(String fullCommand) throws YapperException {
        String[] parts = fullCommand.split(" ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("find");
        }
        String keyword = parts[1];
        List<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return "Sorry, Boss. I couldn't find any tasks matching the keyword '" + keyword + "'.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list, Boss:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append("\n").append(i + 1).append(". ").append(matchingTasks.get(i));
            }
            return result.toString();
        }
    }

    /**
     * Handles user input and returns the response from Bopes.
     *
     * @param input The user input.
     * @return The response from Bopes.
     */
    public String getResponse(String input) {
        try {
            return parse(input, tasks, storage);
        } catch (YapperException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses the user's command and executes the appropriate action.
     *
     * @param fullCommand The full command string entered by the user.
     * @param tasks       The task list that stores all the tasks.
     * @param storage     The storage mechanism to save and load tasks.
     * @return The result of executing the command as a string message.
     * @throws YapperException If an error occurs while processing the command.
     */
    private String parse(String fullCommand, TaskList tasks, Storage storage) throws YapperException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
            case "list":
                return tasks.listTasks();
            case "mark":
                return handleMark(fullCommand);
            case "unmark":
                return handleUnmark(fullCommand);
            case "todo":
                return handleTodo(fullCommand);
            case "deadline":
                return handleDeadline(fullCommand);
            case "event":
                return handleEvent(fullCommand);
            case "delete":
                return handleDelete(fullCommand);
            case "find":
                return handleFind(fullCommand);
            case "bye":
                storage.save(tasks.getTasks());
                return exitApplication();
            default:
                throw new UnknownCommandException(command);
            }
        } catch (IOException e) {
            throw new YapperException("Sorry boss, there was an error saving your data.");
        }
    }

    /**
     * Exits the application after displaying a goodbye message.
     *
     * @return The goodbye message to be displayed to the user.
     */
    private String exitApplication() {
        String goodbyeMessage = "Bye Boss! Hope to see you again soon! Exiting in 3 Seconds!";

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();

        return goodbyeMessage;
    }
}
