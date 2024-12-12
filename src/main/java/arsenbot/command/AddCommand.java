package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.Deadline;
import arsenbot.task.Event;
import arsenbot.task.Task;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.task.Todo;
import arsenbot.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add tasks (Todo, Deadline, or Event) to the task list.
 * The command parses the input and adds the appropriate task type.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Constructs an AddCommand object with the specified user input.
     *
     * @param input the input string from the user
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a task to the task list.
     * Depending on the command type, the appropriate task (ToDo, Deadline, or Event)
     * is created and added to the task list.
     *
     * @param tasks   the task list to which the task will be added
     * @param ui      the UI to display messages
     * @param storage the storage to save tasks to file
     * @return a confirmation message for the added task
     * @throws IOException          if there is an issue saving to storage
     * @throws TaskManagerException if the user input is invalid or incomplete
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        Task task = createTaskFromInput();
        tasks.addTask(task);
        String confirmationMessage = ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
        return confirmationMessage;
    }

    /**
     * Creates a Task object based on the user input.
     *
     * @return the created Task object
     * @throws TaskManagerException if the input is invalid or incomplete
     */
    private Task createTaskFromInput() throws TaskManagerException {
        if (input.startsWith("todo")) {
            return createTodo();
        } else if (input.startsWith("deadline")) {
            return createDeadline();
        } else if (input.startsWith("event")) {
            return createEvent();
        } else {
            throw new TaskManagerException("Error: Unknown command.");
        }
    }

    private Task createTodo() throws TaskManagerException {
        String description = extractDescription();
        return new Todo(description);
    }

    private Task createDeadline() throws TaskManagerException {
        String[] parts = extractParts("deadline", 8, " /by ");
        if (parts.length < 2) {
            throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    private Task createEvent() throws TaskManagerException {
        String[] parts = extractParts("event", 5, " /from ");
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
        }
        return new Event(description, timeParts[0].trim(), timeParts[1].trim());
    }

    private String extractDescription() throws TaskManagerException {
        if (input.length() == 4) {
            throw new TaskManagerException("Error: The " + "todo" + " command requires a description.");
        }
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new TaskManagerException("Error: The description of a '" + "todo" + "' cannot be empty.");
        }
        return description;
    }

    private String[] extractParts(String command, int offset, String delimiter) throws TaskManagerException {
        if (input.length() == offset) {
            throw new TaskManagerException("Error: The " + command + " command requires additional information.");
        }
        return input.substring(offset).split(delimiter);
    }
}
