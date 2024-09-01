package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

/**
 * ListCommand handles commands starting with list
 */
public class ListCommand extends Command {
    public ListCommand(String input) {
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:\n" + tasks.toString();
        ui.printString(response);
        return response;
    }
}
