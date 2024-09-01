package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

/**
 * MarkCommand handles commands starting with mark
 */
public class MarkCommand extends Command {
    private int index;
    public MarkCommand(String input) {
        index = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Nice! I've marked this task as done:\n  " + tasks.markTask(index);
        ui.printString(response);
        return response;
    }
}
