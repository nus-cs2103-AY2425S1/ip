package shoai;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parses user commands and executes the corresponding actions on the task list.
 */
public class Parser {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses a command and executes the appropriate action.
     *
     * @param fullCommand The full command string from the user.
     * @param tasks The TaskList to operate on.
     * @param storage The Storage instance for saving/loading tasks.
     * @return The response string from the executed command or null if the application should exit.
     * @throws ShoAIException If there is an error processing the command.
     */
    public String parse(String fullCommand, TaskList tasks, Storage storage) throws ShoAIException {
        String[] commandParts = fullCommand.split(" ", 2);
        String command = commandParts[0];
        String arguments = commandParts.length > 1 ? commandParts[1] : "";

        switch (command) {
            case "bye":
                return handleBye();
            case "list":
                return handleList(tasks);
            case "mark":
                return handleMark(arguments, tasks, storage);
            case "unmark":
                return handleUnmark(arguments, tasks, storage);
            case "todo":
                return handleTodo(arguments, tasks, storage);
            case "deadline":
                return handleDeadline(arguments, tasks, storage);
            case "event":
                return handleEvent(arguments, tasks, storage);
            case "delete":
                return handleDelete(arguments, tasks, storage);
            case "find":
                return handleFind(arguments, tasks);
            default:
                throw new ShoAIException("Unknown command: " + command);
        }
    }

    private String handleBye() {
        return "Bye bye! Don't forget about me!";
    }

    private String handleList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are all the tasks in your list!\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("%d.%s%n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }

    private String handleMark(String arguments, TaskList tasks, Storage storage) throws ShoAIException {
        validateArguments(arguments, 1);
        int index = parseIndex(arguments);
        Task task = tasks.getTask(index);
        task.markAsDone();
        storage.saveTasks(tasks.getAllTasks());
        return String.format("Good work! I've marked this task as done:%n%s", task);
    }

    private String handleUnmark(String arguments, TaskList tasks, Storage storage) throws ShoAIException {
        validateArguments(arguments, 1);
        int index = parseIndex(arguments);
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        storage.saveTasks(tasks.getAllTasks());
        return String.format("Gotcha! I've marked this task as not done yet:%n%s", task);
    }

    private String handleTodo(String arguments, TaskList tasks, Storage storage) throws ShoAIException {
        validateArguments(arguments, 1);
        Task newTodo = new Todo(arguments.trim());
        tasks.addTask(newTodo);
        storage.saveTasks(tasks.getAllTasks());
        return String.format("No problem! I've added this task:%n%s%nNow you have %d task%s in the list.",
                newTodo, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    private String handleDeadline(String arguments, TaskList tasks, Storage storage) throws ShoAIException {
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new ShoAIException("The description or datetime of the deadline cannot be empty.");
        }
        LocalDateTime deadlineDateTime = parseDateTime(parts[1].trim());
        Task newDeadline = new Deadline(parts[0].trim(), deadlineDateTime);
        tasks.addTask(newDeadline);
        storage.saveTasks(tasks.getAllTasks());
        return String.format("Gotcha! I've added this task:%n%s%nNow you have %d task%s in the list.",
                newDeadline, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    private String handleEvent(String arguments, TaskList tasks, Storage storage) throws ShoAIException {
        String[] parts = arguments.split(" /from ");
        if (parts.length < 2) {
            throw new ShoAIException("The description or start datetime of the event cannot be empty.");
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2 || parts[0].trim().isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new ShoAIException("The description, start datetime, or end datetime of the event cannot be empty.");
        }
        LocalDateTime fromDateTime = parseDateTime(timeParts[0].trim());
        LocalDateTime toDateTime = parseDateTime(timeParts[1].trim());
        Task newEvent = new Event(parts[0].trim(), fromDateTime, toDateTime);
        tasks.addTask(newEvent);
        storage.saveTasks(tasks.getAllTasks());
        return String.format("Gotcha! I've added this task:%n%s%nNow you have %d task%s in the list.",
                newEvent, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    private String handleDelete(String arguments, TaskList tasks, Storage storage) throws ShoAIException {
        validateArguments(arguments, 1);
        int index = parseIndex(arguments);
        Task removedTask = tasks.removeTask(index);
        storage.saveTasks(tasks.getAllTasks());
        return String.format("Wonderful! I've removed this task:%n%s%nNow you have %d task%s in the list.",
                removedTask, tasks.size(), tasks.size() > 1 ? "s" : "");
    }

    private String handleFind(String arguments, TaskList tasks) throws ShoAIException {
        validateArguments(arguments, 1);
        String keyword = arguments.trim();
        StringBuilder response = new StringBuilder("Here you go! These are the matching tasks in your list:\n");
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            response.append(String.format("Uhoh! No tasks found matching the keyword: %s", keyword));
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(String.format("%d.%s%n", i + 1, matchingTasks.get(i)));
            }
        }
        return response.toString();
    }

    private void validateArguments(String arguments, int minParts) throws ShoAIException {
        if (arguments.trim().isEmpty()) {
            throw new ShoAIException("Arguments cannot be empty.");
        }
        if (arguments.split(" ").length < minParts) {
            throw new ShoAIException("Insufficient arguments provided.");
        }
    }

    private int parseIndex(String indexString) throws ShoAIException {
        try {
            return Integer.parseInt(indexString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ShoAIException("Invalid task number format.");
        }
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws ShoAIException {
        try {
            return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ShoAIException("The date and time format is incorrect. Use yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Converts a Task object to a string representation suitable for file storage.
     *
     * @param task The Task object to convert.
     * @return A string representation of the Task object.
     */
    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("T | ");
            sb.append(task.isDone() ? "1" : "0");
            sb.append(" | ");
            sb.append(task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append("D | ");
            sb.append(deadline.isDone() ? "1" : "0");
            sb.append(" | ");
            sb.append(deadline.getDescription()).append(" | ").append(deadline.getBy().format(DATE_TIME_FORMAT));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append("E | ");
            sb.append(event.isDone() ? "1" : "0");
            sb.append(" | ");
            sb.append(event.getDescription())
                    .append(" | ").append(event.getFrom().format(DATE_TIME_FORMAT))
                    .append(" | ").append(event.getTo().format(DATE_TIME_FORMAT));
        }
        return sb.toString();
    }

    /**
     * Converts a string representation from file storage back to a Task object.
     *
     * @param fileString The string representation of a Task object.
     * @return The Task object.
     * @throws ShoAIException If the string representation is invalid.
     */
    public static Task fileStringToTask(String fileString) throws ShoAIException {
        String[] parts = fileString.split(" \\| ");
        if (parts.length < 3) {
            throw new ShoAIException("Invalid task format.");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        switch (type) {
            case "T":
                return new Todo(description);
            case "D":
                LocalDateTime deadlineDateTime = LocalDateTime.parse(parts[3], DATE_TIME_FORMAT);
                return new Deadline(description, deadlineDateTime);
            case "E":
                LocalDateTime eventFromDateTime = LocalDateTime.parse(parts[3], DATE_TIME_FORMAT);
                LocalDateTime eventToDateTime = LocalDateTime.parse(parts[4], DATE_TIME_FORMAT);
                return new Event(description, eventFromDateTime, eventToDateTime);
            default:
                throw new ShoAIException("Unknown task type: " + type);
        }
    }
}
