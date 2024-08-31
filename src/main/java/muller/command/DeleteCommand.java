package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String[] inputs) throws MullerException {
        if (inputs.length < 2 || !isNumeric(inputs[1])) {
            throw new MullerException("Pick a valid task number to delete!");
        }
        this.index = Integer.parseInt(inputs[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
        if (index < 0 || index >= tasks.size()) {
            throw new MullerException("Invalid task number!");
        }

        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(index));
        tasks.deleteTask(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
