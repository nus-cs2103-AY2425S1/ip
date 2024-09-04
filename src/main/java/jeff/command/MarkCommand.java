package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;

/**
 * Represents a command that marks a specific task as completed based on its index in the task list.
 * This command requires a valid, positive integer as input, which corresponds to the task's position
 * in the list.
 */
public class MarkCommand extends Command {
    private String args;

    /**
     * Constructs a {@code MarkCommand} with the specified index argument.
     * The argument must be numeric and correspond to an existing task in the task list.
     *
     * @param args the command arguments expected to be a single numeric string
     *             representing the index of the task to mark.
     * @throws JeffException if {@code args} is empty, not numeric, or otherwise
     *     incorrect, indicating the command was improperly used.
     */
    public MarkCommand(String args) throws JeffException {
        super();
        if (args.isEmpty() || !isNumeric(args)) {
            throw new JeffException("You must provide one number after the command!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        int i = Integer.parseInt(args);
        if (i <= 0 || i > tasks.size()) {
            throw new JeffException("The number is outside the range!");
        }
        tasks.getTask(i - 1).markDone();
        storage.updateSave(tasks.getTasks(), i - 1);
        ui.showMessage("Alrighty, I marked this as done:");
        ui.showMessage("" + tasks.getTask(i - 1));
    }
}
