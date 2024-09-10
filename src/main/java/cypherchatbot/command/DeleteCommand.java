package cypherchatbot.command;

import cypherchatbot.CypherException;
import cypherchatbot.task.Task;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * The Delete class represents a command to delete a task from our Cypher Chat Bot Application
 */
public class DeleteCommand extends Command {

    private int val;

    /**
     * Instantiates a Delete command with a specific index to delte.
     *
     * @param val the specific index of the task to be deleted from the TaskList
     */
    public DeleteCommand(int val) {
        this.val = val;
    }

    /**
     * Executes the Delete command, delete the task from the TaskList,
     * deleting the task from the storage and then finally outputting
     * the result to the user via the Ui output method.
     *
     * @param tasks   The TaskList to which the new Event task should be added.
     * @param ui      The Ui interface used to interact with the user.
     * @param storage The Storage file where the task data will be saved.
     * @return
     * @throws CypherException Throws a custom exception if the index is out of bounds
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CypherException {
        if (this.val >= tasks.size()) {
            throw new CypherException(String.format("You have %d items in your list. "
                    + "Enter a valid integer or add more items to your list", tasks.size()));
        } else if (this.val < 0) {
            throw new CypherException("Enter a value above 0");
        }

        Task deletedTask = tasks.delTask(this.val, storage);
        return ui.showDeleteMessage(deletedTask, tasks.size());
    }

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
