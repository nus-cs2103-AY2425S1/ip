package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.tasks.Task;
import spike.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Creates a new AddTaskCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandType() {
        return "Add Task";
    }

    /**
     * Adds the task to the task list, shows a message to the user, and writes the task list to the file.
     *
     * @param tasks The task list to which the task is to be added.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     * @throws SpikeException If an error occurs while adding the task to the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.writeToFile(tasks);
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
