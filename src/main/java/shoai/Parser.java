package shoai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import shoai.TaskList;
import shoai.Storage;
import shoai.Task;
import shoai.Todo;
import shoai.Deadline;
import shoai.Event;
import shoai.ShoAIException;
import java.util.ArrayList;

/**
 * Parses user commands and executes the corresponding actions on the task list.
 */
public class Parser {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        // Split the full command into 2 parts, first part being the command keyword ie. bye, list, mark
        // Second part being the arguments related to the command
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];
        StringBuilder response = new StringBuilder();

        switch (command) {
            case "bye":
                response.append("Bye bye! Don't forget about me!");
                return response.toString(); // Indicate that the application should exit
            case "list":
                response.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    response.append((i + 1) + "." + tasks.get(i) + "\n");
                }
                break;
            case "mark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to mark.");
                }
                int markIndex = Integer.parseInt(words[1]) - 1;
                Task taskToMark = tasks.getTask(markIndex);
                taskToMark.markAsDone();
                response.append("Nice! I've marked this task as done:\n").append(taskToMark);
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "unmark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to unmark.");
                }
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                Task taskToUnmark = tasks.getTask(unmarkIndex);
                taskToUnmark.markAsNotDone();
                response.append("OK, I've marked this task as not done yet:\n").append(taskToUnmark);
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new ShoAIException("The description of a todo cannot be empty.");
                }
                Task newTodo = new Todo(words[1]);
                tasks.addTask(newTodo);
                response.append("Got it. I've added this task:\n").append(newTodo);
                response.append("\nNow you have ").append(tasks.size()).append(" task").append(tasks.size() > 1 ? "s" : "").append(" in the list.");
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "deadline":
                if (words.length < 2) {
                    throw new ShoAIException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = words[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new ShoAIException("The deadline description or date cannot be empty.");
                }
                LocalDate deadlineDate = LocalDate.parse(deadlineParts[1].trim(), DATE_FORMAT);
                Task newDeadline = new Deadline(deadlineParts[0], deadlineDate);
                tasks.addTask(newDeadline);
                response.append("Got it. I've added this task:\n").append(newDeadline);
                response.append("\nNow you have ").append(tasks.size()).append(" task").append(tasks.size() > 1 ? "s" : "").append(" in the list.");
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "event":
                if (words.length < 2) {
                    throw new ShoAIException("The description of an event cannot be empty.");
                }
                String[] eventParts = words[1].split(" /from ");
                if (eventParts.length < 2) {
                    throw new ShoAIException("The event description or start date cannot be empty.");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2 || eventParts[0].trim().isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                    throw new ShoAIException("The event description, start date, or end date cannot be empty.");
                }
                LocalDate fromDate = LocalDate.parse(timeParts[0].trim(), DATE_FORMAT);
                LocalDate toDate = LocalDate.parse(timeParts[1].trim(), DATE_FORMAT);
                Task newEvent = new Event(eventParts[0], fromDate, toDate);
                tasks.addTask(newEvent);
                response.append("Got it. I've added this task:\n").append(newEvent);
                response.append("\nNow you have ").append(tasks.size()).append(" task").append(tasks.size() > 1 ? "s" : "").append(" in the list.");
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "delete":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to delete.");
                }
                int deleteIndex = Integer.parseInt(words[1]) - 1;
                Task removedTask = tasks.removeTask(deleteIndex);
                response.append("Noted. I've removed this task:\n").append(removedTask);
                response.append("\nNow you have ").append(tasks.size()).append(" task").append(tasks.size() > 1 ? "s" : "").append(" in the list.");
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new ShoAIException("The find command requires a keyword.");
                }
                String keyword = words[1];
                response.append(findTasks(keyword, tasks));
                break;
            default:
                throw new ShoAIException("Sorry, I don't understand that command.");
        }

        return response.toString(); // Return the accumulated response
    }

    /**
     * Finds tasks containing the given keyword and returns the results as a string.
     *
     * @param keyword The keyword to search for.
     * @param tasks The TaskList to search in.
     * @return The response string with the search results.
     */
    private String findTasks(String keyword, TaskList tasks) {
        StringBuilder response = new StringBuilder();
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        response.append("Here are the matching tasks in your list:\n");
        if (matchingTasks.isEmpty()) {
            response.append("No tasks found matching the keyword: ").append(keyword);
        } else {
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1) + "." + matchingTasks.get(i) + "\n");
            }
        }
        return response.toString();
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
            sb.append("D | ");
            Deadline deadline = (Deadline) task;
            sb.append(deadline.isDone() ? "1" : "0");
            sb.append(" | ");
            sb.append(deadline.getDescription()).append(" | ").append(deadline.getBy().format(DATE_FORMAT));
        } else if (task instanceof Event) {
            sb.append("E | ");
            Event event = (Event) task;
            sb.append(event.isDone() ? "1" : "0");
            sb.append(" | ");
            sb.append(event.getDescription()).append(" | ").append(event.getFrom().format(DATE_FORMAT)).append(" | ").append(event.getTo().format(DATE_FORMAT));
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
                LocalDate deadlineDate = LocalDate.parse(parts[3], DATE_FORMAT);
                return new Deadline(description, deadlineDate);
            case "E":
                LocalDate eventFrom = LocalDate.parse(parts[3], DATE_FORMAT);
                LocalDate eventTo = LocalDate.parse(parts[4], DATE_FORMAT);
                return new Event(description, eventFrom, eventTo);
            default:
                throw new ShoAIException("Unknown task type: " + type);
        }
    }
}
