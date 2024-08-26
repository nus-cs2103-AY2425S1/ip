package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(String input) {
        index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Noted. I've removed this task:");
        ui.printString("  " + tasks.deleteTask(index));
        ui.printString("Now you have " + tasks.size() + " tasks in the list.");
    }
}
