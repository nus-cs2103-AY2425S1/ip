package chatsy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.exceptions.InvalidTaskIndexException;
import chatsy.tasks.DeadlineTask;
import chatsy.tasks.EventTask;
import chatsy.tasks.TodoTask;

/**
 * Handles user commands and interacts with the task manager.
 */
public class CommandHandler {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final TaskManager taskManager;

    /**
     * Constructs a {@code CommandHandler} with the specified task manager.
     *
     * @param taskManager The task manager to handle tasks.
     */
    public CommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Processes the given command and performs the corresponding action.
     *
     * @param command The user command.
     * @return A string response to be displayed in the GUI.
     * @throws InvalidCommandException If the command is invalid.
     * @throws EmptyDescriptionException If a task description is empty.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public String handle(String command) throws InvalidCommandException,
            EmptyDescriptionException, InvalidTaskIndexException {
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
        case "bye":
            return "Goodbye!";
        case "list":
            return taskManager.listTasks();
        case "mark":
        case "unmark":
            return handleMarkUnmark(parts, action.equals("mark"));
        case "delete":
            return handleDelete(parts);
        case "todo":
            return handleTodoTask(parts);
        case "deadline":
            return handleDeadlineTask(parts);
        case "event":
            return handleEventTask(parts);
        case "find":
            return handleFindTask(parts);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Handles the mark or unmark command by updating the status of a task.
     *
     * @param parts The command split into parts, where the second part is the task index.
     * @param isMark Whether the action is to mark (true) or unmark (false) the task.
     * @return A string indicating the result of marking/unmarking the task.
     * @throws InvalidTaskIndexException If the task index is invalid or out of bounds.
     */
    private String handleMarkUnmark(String[] parts, boolean isMark) throws InvalidTaskIndexException {
        if (parts.length > 1) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                if (isMark) {
                    taskManager.markTask(taskNumber);
                    return "Nice! I've marked this task as done:\n  " + taskManager.getTasks().get(taskNumber - 1);
                } else {
                    taskManager.unmarkTask(taskNumber);
                    return "OK, I've marked this task as not done yet:\n  "
                            + taskManager.getTasks().get(taskNumber - 1);
                }
            } catch (NumberFormatException e) {
                return "Invalid task number!";
            }
        } else {
            return "Please specify the task number to " + (isMark ? "mark." : "unmark.");
        }
    }

    /**
     * Handles the delete command by removing a task from the task manager.
     *
     * @param parts The command split into parts, where the second part is the task index.
     * @return A string indicating the result of the deletion.
     * @throws InvalidTaskIndexException If the task index is invalid or out of bounds.
     */
    private String handleDelete(String[] parts) throws InvalidTaskIndexException {
        if (parts.length > 1) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                taskManager.deleteTask(taskNumber);
                return "Noted. I've removed this task.\nNow you have "
                        + taskManager.getTasks().size() + " tasks in the list.";
            } catch (NumberFormatException e) {
                return "Invalid task number!";
            }
        } else {
            return "Please specify the task number to delete.";
        }
    }

    /**
     * Handles the todo command by adding a new TodoTask to the task manager.
     *
     * @param parts The command split into parts, where the second part is the task description.
     * @return A string indicating the result of adding the TodoTask.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    private String handleTodoTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            taskManager.addTask(new TodoTask(parts[1]));
            return "Got it. I've added this task.\nNow you have "
                    + taskManager.getTasks().size() + " tasks in the list.";
        } else {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Handles the deadline command by adding a new DeadlineTask with a due date.
     *
     * @param parts The command split into parts, where the second part contains the task description and due date.
     * @return A string indicating the result of adding the DeadlineTask.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    private String handleDeadlineTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            String[] deadlineParts = parts[1].split(" /by ", 2);
            if (deadlineParts.length > 1) {
                try {
                    LocalDate by = LocalDate.parse(deadlineParts[1], DATE_FORMATTER);
                    taskManager.addTask(new DeadlineTask(deadlineParts[0], by));
                    return "Got it. I've added this task.\nNow you have "
                            + taskManager.getTasks().size() + " tasks in the list.";
                } catch (DateTimeParseException e) {
                    return "Please enter the deadline in the correct format (yyyy-MM-dd).";
                }
            } else {
                return "Please specify the deadline in the format: description /by yyyy-MM-dd";
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Handles the event command by adding a new EventTask with a start and end time.
     *
     * @param parts The command split into parts,
     *              where the second part contains the event description, start time, and end time.
     * @return A string indicating the result of adding the EventTask.
     * @throws EmptyDescriptionException If the event description is empty.
     */
    private String handleEventTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            if (eventParts.length == 3) {
                try {
                    LocalDateTime from = LocalDateTime.parse(eventParts[1], DATE_TIME_FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(eventParts[2], DATE_TIME_FORMATTER);
                    taskManager.addTask(new EventTask(eventParts[0], from, to));
                    return "Got it. I've added this task.\nNow you have "
                            + taskManager.getTasks().size() + " tasks in the list.";
                } catch (DateTimeParseException e) {
                    return "Please enter the event times in the correct format (yyyy-MM-dd HH:mm).";
                }
            } else {
                return "Please specify the event in the format: description"
                        + "/from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm";
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Handles the find command by searching for tasks that match the keyword provided by the user.
     *
     * @param parts The command split into parts, where the second part contains the keyword for searching tasks.
     * @return A string listing tasks that match the search keyword, or a message indicating no matches were found.
     * @throws EmptyDescriptionException If the search keyword is empty.
     */
    private String handleFindTask(String[] parts) throws EmptyDescriptionException {
        if (parts.length > 1) {
            String keyword = parts[1];
            String result = taskManager.findTasks(keyword);
            if (result.isEmpty()) {
                return "No matching tasks found.";
            } else {
                return "Here are the matching tasks in your list:\n" + result;
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }
}
