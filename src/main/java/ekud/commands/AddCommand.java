package ekud.commands;

import ekud.components.Storage;
import ekud.components.Ui;
import ekud.task.Task;
import ekud.components.TaskList;

/**
 * Represents a {@link Command} to add a task to some {@link TaskList}.
 *
 * @author uniqly
 */
public class AddCommand extends Command {
    /** The task to be added */
    private final Task task;

    /**
     * Creates the {@link AddCommand} with the given task.
     * @param task The {@link Task} to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);

        String message = String.format("added: %s\nAnd another one; %d out of %d tasks to complete...",
                task,
                tasks.getIncompleteCount(),
                tasks.getCount());
        ui.printOutput(message);

        storage.saveNewTask(task, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
