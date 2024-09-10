package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * This class represents the delete command.
 */
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructs a new {@code DeleteCommand} with the specified task index.
     *
     * @param idx The index of the task to delete as a {@code String}. It should not be empty.
     * @throws JoeException if the provided index is empty or cannot be parsed to an integer.
     */
    public DeleteCommand(String idx) {
        if (idx.isEmpty()) {
            throw new JoeException("Oops! Try: delete {index}");
        }
        this.idx = Integer.parseInt(idx);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        if (idx > taskList.getSize() || idx < 1) { // check that task index is valid
            throw new JoeException("Please input a valid task index");
        }
        taskList.removeTask(idx);
    }
}
