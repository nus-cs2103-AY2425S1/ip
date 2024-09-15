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
        if (CommandUtil.isDeleteCommandValid(inputs)) {
            throw new MullerException("Pick a valid task number to delete!");
        }

        //Resize the index to make sure align with 0-indexed list.
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        CommandUtil.assertionTest(tasks, ui, storage);
        try {
            if (CommandUtil.isValidTaskIndex(index, tasks.getSize())) {
                throw new MullerException("Invalid task number!");
            }
            Task deletedTask = tasks.get(index);
            tasks.deleteTask(index);
            storage.saveTasks(tasks);
            return ui.showTaskDeleted(tasks, deletedTask, index);
        } catch (MullerException e) {
            return e.getMessage();
        }
    }
}
