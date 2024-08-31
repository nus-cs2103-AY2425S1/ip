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
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a valid task number to mark!");
        }
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        tasks.get(index).markAsDone();
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
        ui.showLine();

        storage.save(tasks);
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
