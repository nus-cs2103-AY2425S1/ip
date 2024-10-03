package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified inputs.
     *
     * @param inputs The command inputs.
     * @throws MullerException If the input is not a valid task number.
     */
    public MarkCommand(String[] inputs) throws MullerException {
        if (CommandUtil.isMarkCommandNotValid(inputs)) {
            throw new MullerException("Pick a valid task number to mark!");
        }
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        CommandUtil.assertionTest(tasks, ui, storage);
        if (!CommandUtil.isTaskIndexValid(index, tasks.getSize())) {
            throw new MullerException("Invalid task number!");
        }
        tasks.get(index).markAsDone();
        storage.saveTasks(tasks);
        return ui.showTaskMarked(tasks, index);
    }
}

