package command;

import assertions.AssertCommand;
import components.Storage;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to mark a task as done or undone.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMark;

    /**
     * Creates a MarkCommand object.
     *
     * @param taskNumber The number of the task to be marked.
     * @param isMark     True if the task is to be marked as done, false if it is to be marked as undone.
     */
    public MarkCommand(int taskNumber, boolean isMark) {
        super();
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    /**
     * Marks the task as done or undone and updates the storage file.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws LightException if an error occurs during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        String reply;
        if (isMark) {
            tasks.get(taskNumber).markAsDone();
            reply = ui.beautifyMessage("Nice! I've marked this task as done:\n" + tasks.get(taskNumber));

        } else {
            tasks.get(taskNumber).markAsUndone();
            reply = ui.beautifyMessage("Nice! I've marked this task as undone:\n" + tasks.get(taskNumber));
        }
        storage.write(TaskList.arrayToNumberedString(tasks));
        return reply;
    }
}
