package shoai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import shoai.TaskList;
import shoai.Ui;
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
     * @param ui The Ui instance for user interaction.
     * @param storage The Storage instance for saving/loading tasks.
     * @return true if the command is "bye" and the application should exit, false otherwise.
     * @throws ShoAIException If there is an error processing the command.
     */
    public boolean parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws ShoAIException {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "bye":
                ui.showExitMessage();
                return true; // Exit the loop
            case "list":
                ui.showTaskList(tasks);
                break;
            case "mark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to mark.");
                }
                int markIndex = Integer.parseInt(words[1]) - 1;
                Task taskToMark = tasks.getTask(markIndex);
                taskToMark.markAsDone();
                ui.showTaskMarked(taskToMark);
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "unmark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to unmark.");
                }
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                Task taskToUnmark = tasks.getTask(unmarkIndex);
                taskToUnmark.markAsNotDone();
                ui.showTaskUnmarked(taskToUnmark);
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new ShoAIException("The description of a todo cannot be empty.");
                }
                Task newTodo = new Todo(words[1]);
                tasks.addTask(newTodo);
                ui.showTaskAdded(newTodo, tasks.size());
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
                ui.showTaskAdded(newDeadline, tasks.size());
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
                ui.showTaskAdded(newEvent, tasks.size());
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "delete":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to delete.");
                }
                int deleteIndex = Integer.parseInt(words[1]) - 1;
                Task removedTask = tasks.removeTask(deleteIndex);
                ui.showTaskDeleted(removedTask, tasks.size());
                storage.saveTasks(tasks.getAllTasks());
                break;
            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new ShoAIException("The find command requires a keyword.");
                }
                String keyword = words[1];
                findTasks(keyword, tasks, ui);
                break;
            default:
                throw new ShoAIException("Sorry, I don't understand that command.");
        }

        return false; // Continue the loop for other commands
    }

    /**
     * Finds tasks containing the given keyword and displays them.
     *
     * @param keyword The keyword to search for.
     * @param tasks The TaskList to search in.
     * @param ui The Ui instance for user interaction.
     */
    private void findTasks(String keyword, TaskList tasks, Ui ui) {
        ui.showFindResults(tasks, keyword);
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
     * @return The corresponding Task object.
     */
    public static Task fileStringToTask(String fileString) {
        String[] parts = fileString.split(" \\| ");
        Task task = null;
        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                LocalDate deadlineDate = LocalDate.parse(parts[3], DATE_FORMAT);
                task = new Deadline(parts[2], deadlineDate);
                break;
            case "E":
                LocalDate eventFromDate = LocalDate.parse(parts[3], DATE_FORMAT);
                LocalDate eventToDate = LocalDate.parse(parts[4], DATE_FORMAT);
                task = new Event(parts[2], eventFromDate, eventToDate);
                break;
        }
        if (task != null && parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
