package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

/**
 * This class is used to mark a completed task.
 */
public class MarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a new {@code MarkCommand} with the specified task index.
     *
     * @param idx The index of the task to mark as completed, provided as a {@code String}. It should not be empty.
     * @throws JoeException if the provided index is empty or cannot be parsed into an integer.
     */
    public MarkCommand(String idx) {
        if (idx.isEmpty()) {
            throw new JoeException("Oops! Try: mark {index}");
        }
        this.idx = Integer.parseInt(idx);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        if (idx > taskList.getSize() || idx < 1) { // check that task index is valid
            throw new JoeException("Please input a valid task index");
        }
        taskList.markTask(idx);
    }

    public static String getHelp() {
        return "To mark a complete task, try: mark {index}";
    }
}
