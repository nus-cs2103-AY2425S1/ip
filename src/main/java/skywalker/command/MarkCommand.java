package skywalker.command;
import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        if (isMark) {
            task.markDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        storage.save(tasks);
    }
}
