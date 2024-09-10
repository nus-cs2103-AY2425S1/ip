package cypherchatbot.command;

import cypherchatbot.CypherException;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * Represents a command to mark a task as completed.
 * The command marks a specific task as complete by its index in the TaskList.
 */
public class MarkCommand extends Command {
    private int val;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param val The index of the task to be marked as completed.
     */
    public MarkCommand(int val) {
        this.val = val;
    }

    /**
     * Executes the MarkCommand by marking the task at the specified index as completed.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui used to interact with the user and display messages.
     * @param storage The Storage used for saving task data.
     * @return
     * @throws CypherException If the provided index is invalid or out of bounds.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CypherException {
        if (this.val >= tasks.size()) {
            throw new CypherException(String.format("You have %d items in your list. "
                    + "Enter a valid integer or add more items to your list", tasks.size()));
        } else if (this.val < 0) {
            throw new CypherException("Enter a value above 0");
        }
        Task markedTask = tasks.markTask(this.val, storage);
        return ui.showMarkedMessage(markedTask);
    }

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }


}
