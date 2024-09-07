package parser;

import enums.CommandName;
import exceptions.JarException;
import tasks.*;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class is responsible for interpreting user commands and
 * converting them into actionable tasks or operations within the Jar Bot application.
 */
public class Parser {

    /**
     * Extracts the task number from a given command string.
     *
     * @param command The command string containing the task number.
     * @return The task number as an integer.
     * @throws JarException If the task number is invalid or incorrectly formatted.
     */
    public int getTaskNumber(String command) throws JarException {
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            try {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber < 0) {
                    throw new JarException("tasks.Task number must be greater than 0.");
                }
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new JarException("Invalid task number format.");
            }
        } else {
            throw new JarException("Invalid task command format.");
        }
    }

    /**
     * Parses a command string and converts it into a Task object.
     *
     * @param command The command string to be parsed.
     * @return The corresponding Task object.
     * @throws JarException If the command is unknown or incorrectly formatted.
     */
    public Task parseTask(String command) throws JarException {
        CommandName commandType = parseCommandType(command);

        switch (commandType) {
        case TODO:
            return parseToDoTask(command);
        case DEADLINE:
            return parseDeadlineTask(command);
        case EVENT:
            return parseEventTask(command);
        default:
            throw new JarException("Unknown command: " + command + ". Please enter a valid command.");
        }
    }

    /**
     * Parses a TODO task from the given command string.
     *
     * @param command The command string to be parsed.
     * @return The ToDo task created from the command string.
     * @throws JarException If the todo description is empty.
     */
    private Task parseToDoTask(String command) throws JarException {
        String todoDescription = command.substring(4).trim();
        if (todoDescription.isEmpty()) {
            throw new JarException("The description of a todo cannot be empty.");
        }
        return new ToDo(todoDescription);
    }

    /**
     * Parses a Deadline task from the given command string.
     *
     * @param command The command string to be parsed.
     * @return The Deadline task created from the command string.
     * @throws JarException If the deadline format is invalid.
     */
    private Task parseDeadlineTask(String command) throws JarException {
        String[] deadlineParts = command.substring(8).split("/by", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new JarException("Invalid deadline format. Use: deadline <description> /by <date>");
        }
        String deadlineDescription = deadlineParts[0].trim();
        LocalDateTime deadlineDateTime = parseDateTime(deadlineParts[1].trim());
        return new DeadLine(deadlineDescription, deadlineDateTime);
    }

    /**
     * Parses an Event task from the given command string.
     *
     * @param command The command string to be parsed.
     * @return The Event task created from the command string.
     * @throws JarException If the event format is invalid.
     */
    private Task parseEventTask(String command) throws JarException {
        String[] eventParts = command.substring(5).split("/from", 2);
        if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
            throw new JarException("Invalid event format. Use: event <description> /from <start time> /to <end time>");
        }
        String eventDescription = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("/to", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new JarException("Invalid event time format. Use: event <description> /from <start time> /to <end time>");
        }
        return new Event(eventDescription, timeParts[0].trim(), timeParts[1].trim());
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date and time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws JarException If the date and time format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws JarException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new JarException("Invalid date-time format. Use: d/M/yyyy HHmm.");
        }
    }

    /**
     * Handles a user command by interpreting it and performing the corresponding action.
     *
     * @param command  The user command string.
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string containing the response to the command.
     * @throws JarException If the command is unknown or if an error occurs during command handling.
     */
    public String handleCommand(String command, TaskList taskList, Ui ui) throws JarException {
        CommandName commandType = parseCommandType(command);

        switch (commandType) {
        case EXIT:
            return handleExitCommand();
        case LIST:
            return handleListCommand(taskList, ui);
        case MARK:
            return handleMarkCommand(command, taskList, ui);
        case UNMARK:
            return handleUnmarkCommand(command, taskList, ui);
        case DELETE:
            return handleDeleteCommand(command, taskList, ui);
        case TODO:
            //Fallthrough
        case DEADLINE:
            //Fallthrough
        case EVENT:
            return handleTaskCreationCommand(command, taskList, ui);
        case FIND:
            return handleFindCommand(command, taskList, ui);
        default:
            throw new JarException("Unknown command: " + command + ". Please enter a valid command.");
        }
    }

    /**
     * Handles the "exit" command.
     *
     * @return A string indicating the exit command.
     */
    private String handleExitCommand() {
        return "exit";
    }

    /**
     * Handles the "list" command, which shows the list of tasks.
     *
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string containing the list of tasks.
     */
    private String handleListCommand(TaskList taskList, Ui ui) {
        return ui.showTaskList(taskList.listTasks());
    }

    /**
     * Handles the "mark" command, which marks a task as done.
     *
     * @param command  The user command string.
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string indicating the task has been marked as done.
     * @throws JarException If the task number is invalid.
     */
    private String handleMarkCommand(String command, TaskList taskList, Ui ui) throws JarException {
        int markNumber = getTaskNumber(command);
        Task markTask = taskList.getTask(markNumber);
        if (markTask != null) {
            taskList.markTaskAsDone(markNumber);
            return ui.showTaskMarked(markTask);
        } else {
            throw new JarException("Invalid task number.");
        }
    }

    /**
     * Handles the "unmark" command, which marks a task as undone.
     *
     * @param command  The user command string.
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string indicating the task has been marked as undone.
     * @throws JarException If the task number is invalid.
     */
    private String handleUnmarkCommand(String command, TaskList taskList, Ui ui) throws JarException {
        int unmarkNumber = getTaskNumber(command);
        Task unmarkTask = taskList.getTask(unmarkNumber);
        if (unmarkTask != null) {
            taskList.markTaskAsUndone(unmarkNumber);
            return ui.showTaskUnmarked(unmarkTask);
        } else {
            throw new JarException("Invalid task number.");
        }
    }

    /**
     * Handles the "delete" command, which deletes a task from the list.
     *
     * @param command  The user command string.
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string indicating the task has been deleted.
     * @throws JarException If the task number is invalid.
     */
    private String handleDeleteCommand(String command, TaskList taskList, Ui ui) throws JarException {
        int deleteNumber = getTaskNumber(command);
        Task deleteTask = taskList.getTask(deleteNumber);
        taskList.deleteTask(deleteNumber);
        return ui.showDeleteTask(deleteTask) + "\n" + ui.showTaskCount(taskList.getTaskCount());
    }

    /**
     * Handles the creation of a new task (TODO, DEADLINE, or EVENT).
     *
     * @param command  The user command string.
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string indicating the task has been added.
     * @throws JarException If the command format is invalid.
     */
    private String handleTaskCreationCommand(String command, TaskList taskList, Ui ui) throws JarException {
        Task task = parseTask(command);
        taskList.addTask(task);
        return ui.showTaskAdded(task.toString()) + "\n" + ui.showTaskCount(taskList.getTaskCount());
    }

    /**
     * Handles the "find" command, which finds tasks matching a keyword.
     *
     * @param command  The user command string.
     * @param taskList The list of tasks in the Jar Bot application.
     * @param ui       The user interface used to interact with the user.
     * @return A string containing the list of matching tasks.
     */
    private String handleFindCommand(String command, TaskList taskList, Ui ui) {
        String keyword = command.substring(4).trim();  // Extract keyword from command
        try {
            ArrayList<Task> foundTasks = taskList.findTasks(keyword);  // Find tasks with the keyword
            return ui.showTaskList(foundTasks);  // Display the list of found tasks
        } catch (JarException e) {
            return "Error: " + e.getMessage();  // Handle case where keyword is invalid
        }
    }

    /**
     * Determines the type of command based on the command string.
     *
     * @param command The command string to be interpreted.
     * @return The corresponding CommandName enum value.
     */
    public CommandName parseCommandType(String command) {
        if (command.startsWith("todo")) {
            return CommandName.TODO;
        } else if (command.startsWith("deadline")) {
            return CommandName.DEADLINE;
        } else if (command.startsWith("event")) {
            return CommandName.EVENT;
        } else if (command.startsWith("mark")) {
            return CommandName.MARK;
        } else if (command.startsWith("unmark")) {
            return CommandName.UNMARK;
        } else if (command.startsWith("delete")) {
            return CommandName.DELETE;
        } else if (command.equalsIgnoreCase("list")) {
            return CommandName.LIST;
        } else if (command.equalsIgnoreCase("bye")) {
            return CommandName.EXIT;
        } else if (command.startsWith("find")) {
            return CommandName.FIND;
        } else {
            return CommandName.UNKNOWN;
        }
    }
}
