package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

public class MarkCommand extends Command {
    int index;
    public MarkCommand(String input) {
        index = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Nice! I've marked this task as done:");
        ui.printString("  " + tasks.markTask(index));
    }
}
