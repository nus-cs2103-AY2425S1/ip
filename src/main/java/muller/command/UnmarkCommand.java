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
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a valid task number to unmark!");
        }
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        tasks.get(index).markAsNotDone();
        storage.saveTasks(tasks);
        return ui.showTaskUnMarked(tasks, index);
    }

    /**
     * Checks if the input string is a numeric value.
     *
     * @param str The input string.
     * @return True if the string is numeric, false otherwise.
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
