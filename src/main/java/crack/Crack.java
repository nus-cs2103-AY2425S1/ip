package crack;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * The Crack class serves as the main entry point for the application.
 * It initializes the user interface (Ui), task storage (Storage),
 * and task management (TaskList). It also contains the main logic that
 * processes user commands and generates responses.
 */
public class Crack {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Crack instance, initializing the Ui, Storage,
     * and TaskList. Attempts to load tasks from the file specified
     * by the filePath. If loading fails, an empty task list is initialized.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Crack(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(ui));
        } catch (IOException e) {
            ui.showError("Unable to load tasks from file.");
            tasks = new TaskList(); // Initialize with an empty task list if load fails
        }
    }

    /**
     * Generates a response for the user's chat message by parsing the input
     * and delegating the command handling to specific methods.
     *
     * @param input The user's input string.
     * @return The response string to be displayed in the GUI.
     */
    public String getResponse(String input) {
        String command = Parser.parseCommand(input); // Get the command part of the input

        switch (command) {
        case "bye":
            return handleByeCommandResponse();
        case "list":
            return handleListCommandResponse();
        case "mark":
            return handleMarkCommandResponse(input);
        case "unmark":
            return handleUnmarkCommandResponse(input);
        case "todo":
            return handleTodoCommandResponse(input);
        case "deadline":
            return handleDeadlineCommandResponse(input);
        case "event":
            return handleEventCommandResponse(input);
        case "find":
            return handleFindCommandResponse(input);
        case "delete":
            return handleDeleteCommandResponse(input);
        case "snooze":
            return handleSnoozeCommandResponse(input);
        default:
            return ui.showError("Invalid Command.");
        }
    }

    /**
     * Handles the "bye" command and returns the goodbye message.
     *
     * @return The goodbye message.
     */
    private String handleByeCommandResponse() {
        return ui.showGoodbye();
    }

    /**
     * Handles the "list" command and returns the list of tasks.
     *
     * @return The list of tasks or a message indicating the list is empty.
     */
    private String handleListCommandResponse() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        } else {
            return "Here are the tasks in your list:\n" + tasks.listTasks();
        }
    }

    /**
     * Handles the "mark" command by marking a task as done and returns a response message.
     *
     * @param input The user input containing the task number to mark as done.
     * @return A response message indicating the result.
     */
    private String handleMarkCommandResponse(String input) {
        try {
            int index = Parser.parseTaskNumber(input);
            tasks.getTask(index).markAsDone();
            storage.saveTasks(tasks.getTasks(), ui);
            return "Nice! I've marked this task as done:\n   " + tasks.getTask(index);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "unmark" command by marking a task as not done and returns a response message.
     *
     * @param input The user input containing the task number to unmark.
     * @return A response message indicating the result.
     */
    private String handleUnmarkCommandResponse(String input) {
        try {
            int index = Parser.parseTaskNumber(input);
            tasks.getTask(index).unmark();
            storage.saveTasks(tasks.getTasks(), ui);
            return "OK, I've marked this task as not done yet:\n   " + tasks.getTask(index);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "todo" command by adding a new Todo task and returns a response message.
     *
     * @param input The user input containing the description of the Todo task.
     * @return A response message indicating the result.
     */
    private String handleTodoCommandResponse(String input) {
        try {
            String description = Parser.parseTodoDescription(input);
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);
            storage.saveTasks(tasks.getTasks(), ui);
            return ui.showTaskAdded(newTodo, tasks.getSize());
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task and returns a response message.
     *
     * @param input The user input containing the description and deadline date.
     * @return A response message indicating the result.
     */
    private String handleDeadlineCommandResponse(String input) {
        try {
            String[] deadlineDetails = Parser.parseDeadline(input);
            Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            tasks.addTask(newDeadline);
            storage.saveTasks(tasks.getTasks(), ui);
            return ui.showTaskAdded(newDeadline, tasks.getSize());
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "event" command by adding a new Event task and returns a response message.
     *
     * @param input The user input containing the description, start date, and end date.
     * @return A response message indicating the result.
     */
    private String handleEventCommandResponse(String input) {
        try {
            String[] eventDetails = Parser.parseEvent(input);
            Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
            tasks.addTask(newEvent);
            storage.saveTasks(tasks.getTasks(), ui);
            return ui.showTaskAdded(newEvent, tasks.getSize());
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "find" command by searching for tasks that match a keyword and returns a response message.
     *
     * @param input The user input containing the keyword to search for.
     * @return A response message with the matching tasks.
     */
    private String handleFindCommandResponse(String input) {
        try {
            String keyword = input.substring(5).trim(); // Extract the keyword
            if (keyword.isEmpty()) {
                throw new IllegalArgumentException("Keyword cannot be empty.");
            }
            return ui.showMatchingTasks(tasks.findTasks(keyword));
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "delete" command by removing a specified task and returns a response message.
     *
     * @param input The user input containing the task number to delete.
     * @return A response message indicating the result.
     */
    private String handleDeleteCommandResponse(String input) {
        try {
            int index = Parser.parseTaskNumber(input);
            Task removedTask = tasks.removeTask(index);
            storage.saveTasks(tasks.getTasks(), ui);
            return "Noted. I've removed this task:\n   " + removedTask;
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "snooze" command by updating the date of a specified task and returns a response message.
     *
     * @param input The user input containing the task number and new date.
     * @return A response message indicating the result.
     */
    private String handleSnoozeCommandResponse(String input) {
        try {
            String[] snoozeDetails = Parser.parseSnoozeCommand(input);
            int index = Integer.parseInt(snoozeDetails[0]);
            String newDate = snoozeDetails[1];

            Task task = tasks.getTask(index);
            task.updateDate(newDate);

            storage.saveTasks(tasks.getTasks(), ui);
            return "Task has been postponed:\n   " + task;
        } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use yyyy-mm-dd.";
        }
    }
}
