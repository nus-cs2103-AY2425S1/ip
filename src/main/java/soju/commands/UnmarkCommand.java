package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

/**
 * UnmarkCommand handles commands starting with unmark
 */
public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(String input) {
        index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "OK, I've marked this task as not done yet:\n  "
                + tasks.unmarkTask(index);
        ui.printString(response);
        return response;
    }
}
