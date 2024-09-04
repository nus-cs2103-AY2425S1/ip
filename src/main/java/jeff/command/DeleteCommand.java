package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;

/**
 * Represents a command that deletes a specific task from the task list based on its index.
 * The {@code DeleteCommand} is initialized with arguments that should contain a single numeric index.
 *
 * <p>This index corresponds to the position of the task in the task list, starting from 1.
 * If the provided index is not numeric, out of range, or the input is empty, an exception is thrown.</p>
 */
public class DeleteCommand extends Command {
    private String args;

    /**
     * Constructs a {@code DeleteCommand} with the specified index arguments.
     *
     * @param args the command arguments expected to be a single numeric string representing the index.
     * @throws JeffException if {@code args} is empty, not numeric, or otherwise incorrect.
     */
    public DeleteCommand(String args) throws JeffException {
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

        ui.showMessage("Ok, I will delete this task:");
        ui.showMessage("" + tasks.getTask(i - 1));
        tasks.deleteTask(i - 1);
        storage.updateSave(tasks.getTasks());
    }
}
