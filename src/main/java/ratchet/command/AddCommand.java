package ratchet.command;

import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to add the task to task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        return "Got it. I've added this task:" + ui.printWithSeparator(task.toString())
                + ui.printWithSeparator(tasks.toString());
    }
}
