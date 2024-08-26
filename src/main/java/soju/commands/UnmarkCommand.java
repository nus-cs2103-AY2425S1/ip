package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(String input) {
        index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("OK, I've marked this task as not done yet:");
        ui.printString("  " + tasks.unmarkTask(index));
    }
}
