package skywalker.command;

import java.io.IOException;

import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;


/**
 * customise the command to mark command
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

    /**
     * Initialise
     * @param index index of the event
     * @param isMark marked or not
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * mark the task as done and prints a message
     * @param tasks   The task list on which the command will operate.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save or load the task list.
     * @throws IOException Handles the case whereby the I/O is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        String result;
        if (isMark) {
            task.markDone();
            result = "Nice! I've marked this task as done:\n" + task;
        } else {
            task.unmarkDone();
            result = "OK, I've marked this task as not done yet:\n" + task;
        }
        storage.save(tasks);
        return result;
    }

}
