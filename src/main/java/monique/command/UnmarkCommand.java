package monique.command;

import monique.exception.MarkException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents a command for unmarking a task in the task list.
 * This command is used to change the status of a task from completed to not completed.
 */
public class UnmarkCommand extends Command {
    private final int taskNum;

    /**
     * Constructs an <code>UnmarkCommand</code> with the specified task number.
     *
     * @param taskNum the index of the task to unmark
     */
    public UnmarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * Executes the <code>UnmarkCommand</code> by unmarking the task at the specified index.
     * Throws <code>MarkException</code> if the task number is invalid.
     *
     * @param tasks the <code>TaskList</code> to operate on
     * @param ui the <code>Ui</code> instance to handle user interactions
     * @param storage the <code>Storage</code> instance to manage data persistence
     * @throws MarkException if the task number is out of range or invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MarkException {
        if (this.taskNum > tasks.getNumItems() - 1 || this.taskNum < 0) {
            throw new MarkException();
        }
        tasks.unmarkTask(this.taskNum);
        ui.unmarkMessage(tasks.getTask(this.taskNum));
    }

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true since bot should remain active after the UnMark Command
     */
    @Override
    public boolean isActive() {
        return true;
    }
}
