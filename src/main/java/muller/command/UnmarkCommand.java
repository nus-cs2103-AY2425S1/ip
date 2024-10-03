package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to unmark a task (mark it as not done) in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified inputs.
     *
     * @param inputs The command inputs.
     * @throws MullerException If the input is not a valid task number.
     */
    public UnmarkCommand(String[] inputs) throws MullerException {
        if (CommandUtil.isMarkCommandNotValid(inputs)) {
            throw new MullerException("Pick a valid task number to unmark!");
        }
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        CommandUtil.assertionTest(tasks, ui, storage);
        if (!CommandUtil.isTaskIndexValid(index, tasks.getSize())) {
            throw new MullerException("Invalid task number!");
        }
        tasks.get(index).markAsNotDone();
        storage.saveTasks(tasks);
        return ui.showTaskUnMarked(tasks, index);
    }
}

