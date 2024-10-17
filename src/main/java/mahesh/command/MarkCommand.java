package mahesh.command;

import java.io.IOException;

import mahesh.util.Storage;
import mahesh.util.TaskList;

/**
 * Represents a command to mark or unmark a task as done in the TaskList and update the Storage.
 */
public class MarkCommand extends Command {
    private final TaskList list;
    private final Storage store;
    private final int index;
    private final boolean isMark;

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
     *
     * @return a String response indicating the result of marking or unmarking the task
     */
    @Override
    public String execute() {
        String response;
        if (this.isMark) {
            response = list.markTaskAsDone(index);
        } else {
            response = list.unmarkTaskAsDone(index);
        }
        try {
            store.updateData(list);
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }
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
