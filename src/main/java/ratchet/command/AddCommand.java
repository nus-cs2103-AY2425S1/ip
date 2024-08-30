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
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.printLine();
        ui.printWithIndent("Got it. I've added this task:");
        ui.printWithIndent(task.toString());
        ui.printWithIndent(tasks.toString());
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
