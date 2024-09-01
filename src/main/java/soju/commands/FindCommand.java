package soju.commands;

import soju.SojuException;
import soju.Storage;
import soju.TaskList;
import soju.Ui;

/**
 * FindCommand handles commands starting with find
 */
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String input) {
        keyword = input.split(" ", 2)[1];
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SojuException {
        String response = "Here are the matching tasks in your list:\n";
        response += tasks.findMatchingTasks(keyword);
        ui.printString(response);
        return response;
    }
}
