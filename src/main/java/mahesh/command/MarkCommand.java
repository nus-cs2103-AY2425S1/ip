package mahesh.command;

import mahesh.util.Storage;
import mahesh.util.TaskList;

/**
 * Represents a command to mark or unmark a task as done in the TaskList and update the Storage.
 */
public class MarkCommand extends Command {
    private TaskList list;
    private Storage store;
    private int index;
    private boolean isMark;

    /**
     * Constructs a MarkCommand with the specified TaskList, Storage, task index, and mark status.
     *
     * @param list   the TaskList containing the task to be marked or unmarked
     * @param store  the Storage to update after marking or unmarking
     * @param index  the index of the task to be marked or unmarked
     * @param isMark true if the task is to be marked as done, false if it is to be unmarked
     */
    public MarkCommand(TaskList list, Storage store, int index, boolean isMark) {
        this.list = list;
        this.store = store;
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * Executes the MarkCommand by marking or unmarking the task as done in the TaskList and updating the Storage.
     */
    public void execute() {
        if (this.isMark) {
            list.markTaskAsDone(index);
        } else {
            list.unmarkTaskAsDone(index);
        }
        store.updateData(list);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as MarkCommand is not an exit command
     */
    public boolean isExit() {
        return false;
    }
}
