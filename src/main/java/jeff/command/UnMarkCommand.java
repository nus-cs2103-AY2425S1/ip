package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;

/**
 * Represents a command that unmarks a specific task as not completed based on its index in the task list.
 * This command requires a valid, positive integer as input, which corresponds to the task's position
 * in the list.
 */
public class UnMarkCommand extends Command {
    private String args;

    /**
     * Constructs a {@code UnMarkCommand} with the specified index argument.
     * The argument must be numeric and correspond to an existing task in the task list.
     *
     * @param args the command arguments expected to be a single numeric string representing
     *             the index of the task to unmark.
     * @throws JeffException if {@code args} is empty, not numeric, or otherwise incorrect,
     *             indicating the command was improperly used.
     */
    public UnMarkCommand(String args) throws JeffException {
        super();
        if (args.isEmpty() || !isNumeric(args)) {
            throw new JeffException("One number after the command, you must provide!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        int i = Integer.parseInt(args);
        if (i <= 0 || i > tasks.size()) {
            throw new JeffException("Outside the range, this number is!");
        }
        tasks.getTask(i - 1).markNotDone();
        storage.updateSave(tasks.getTasks(), i - 1);
        ui.showMessage("Marked this as not done, I have:");
        ui.showMessage("" + tasks.getTask(i - 1));
    }
}
