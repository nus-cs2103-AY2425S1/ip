package soju.commands;

import soju.Storage;
import soju.TaskList;
import soju.Ui;

/**
 * DeleteCommand handles commands starting with delete
 */
public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(String input) {
        index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        response += "Noted. I've removed this task:\n";
        response += "  " + tasks.deleteTask(index) + "\n";
        response += "Now you have " + tasks.size() + " tasks in the list.";
        ui.printString(response);
        return response;
    }
}
