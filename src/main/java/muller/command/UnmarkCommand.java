package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String[] inputs) throws MullerException {
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a valid task number to unmark!");
        }
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        tasks.get(index).markAsNotDone();
        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(index));
        ui.showLine();

        storage.save(tasks);
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
