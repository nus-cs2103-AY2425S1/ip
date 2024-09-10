
package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * This class is used to unmark a completed task.
 */
public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a new {@code UnmarkCommand} with the specified task index.
     *
     * @param idx The index of the task to unmark as not completed, provided as a {@code String}.
     * @throws JoeException if the provided index is empty or cannot be parsed into an integer.
     */
    public UnmarkCommand(String idx) {
        if (idx.isEmpty()) {
            throw new JoeException("Oops! Try: unmark {index}");
        }
        this.idx = Integer.parseInt(idx);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        if (idx > taskList.getSize() || idx < 1) { // check that task index is valid
            throw new JoeException("Please input a valid task index");
        }
        taskList.unmarkTask(idx);
    }
}
