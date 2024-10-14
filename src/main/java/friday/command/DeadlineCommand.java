package friday.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import friday.task.Deadline;
import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs a DeadlineCommand with the specified input.
     *
     * @param inputs The input array containing the description and deadline date.
     * @throws FridayException If the input is invalid.
     */
    public DeadlineCommand(String[] inputs) {
        if (inputs.length != 2) {
            throw new FridayException("\tInvalid input. 'deadline' command requires a"
                    + " description and deadline\n\tusage: deadline <string> /by <yyyy-MM-dd HHmm>.");
        }
        this.description = inputs[0];
        this.by = inputs[1];
    }

    /**
     * Executes the deadline command, adding the deadline task to the task list and saving it.
     *
     * @param tasks   The task list to be modified by the command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list.
     * @return The string representation of adding a new task to the task list.
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        try {
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            storage.saveTasks(tasks.getTasks());
            return ui.showTaskAdded(task, tasks.getSize());
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use the format: yyyy-MM-dd HHmm.";
        }
    }
}
