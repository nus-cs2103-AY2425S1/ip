package friday.command;

import friday.task.Deadline;
import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
            throw new FridayException("\tInvalid input. 'deadline' command requires a" +
                    " description and deadline\n\tusage: deadline <string> /by <yyyy-MM-dd HHmm>.");
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
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        try {
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
        } catch (DateTimeParseException e) {
            System.out.println("\tInvalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }
}
