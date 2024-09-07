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
     * Construct an AddCommand object with the specified user input.
     *
     * @param input the input string from the user
     */
    public AddCommand(String input) {
        this.input = input;
    }


    /**
     * Executes the command to add a task to the task list.
     * Depending on the command type, the appropriate task (ToDo, Deadline or Event)
     * is created and added to the task list.
     * @param tasks the task list to which the task will be added
     * @param ui the UI to display messages
     * @param storage the storage to save tasks to file
     * @throws IOException          if there is an issue saving to storage
     * @throws TaskManagerException if the user input is invalid or incomplete
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        Task task;
        if (input.startsWith("todo")) {
            // Check if there is enough length for a description
            if (input.length() == 4) {
                throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
            }
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
            }
            task = new Todo(description);
        } else if (input.startsWith("deadline")) {
            if (input.length() == 8) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            String[] parts = input.substring(8).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new TaskManagerException("Error: The deadline command requires both a task description and a '/by' date.");
            }
            task = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (input.startsWith("event")) {
            if (input.length() == 5) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String[] parts = input.substring(5).split(" /from ");
            if (parts.length < 2 || parts[0].trim().isEmpty() || !parts[1].contains(" /to ")) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            String description = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts[1].isEmpty()) {
                throw new TaskManagerException("Error: The event command requires a description, start time, and end time.");
            }
            task = new Event(description, timeParts[0].trim(), timeParts[1].trim());
        } else {
            throw new TaskManagerException("Error: Unknown command.");
        }

        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
