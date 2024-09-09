package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.tasks.Task;
import spike.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new DeleteTaskCommand with the given task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandType() {
        return "Delete Task";
    }

    /**
     * Deletes the task from the task list, shows a message to the user, and writes the task list to the file.
     *
     * @param tasks The task list from which the task is to be deleted.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     * @throws SpikeException If an error occurs while deleting the task from the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            Task task = tasks.deleteTask(taskIndex);
            ui.showTaskDeleted(task, tasks.getSize());
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
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
