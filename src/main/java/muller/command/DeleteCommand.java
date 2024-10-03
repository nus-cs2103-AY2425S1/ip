package muller.command;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified inputs.
     *
     * @param inputs The command inputs.
     * @throws MullerException If the input is not a valid task number.
     */
    public DeleteCommand(String[] inputs) throws MullerException {
        if (CommandUtil.isDeleteCommandNotValid(inputs)) {
            throw new MullerException("Pick a valid task number to delete!");
        }
        // Adjust the index for 0-based indexing
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        CommandUtil.assertionTest(tasks, ui, storage);
        if (!CommandUtil.isTaskIndexValid(index, tasks.getSize())) {
            throw new MullerException("Invalid task number!");
        }
        Task taskToDelete = tasks.get(index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks);
        return ui.showTaskDeleted(tasks, taskToDelete, index);
    }
}

