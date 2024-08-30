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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SojuException {
        ui.printString("Here are the matching tasks in your list:");
        ui.printString(tasks.findMatchingTasks(keyword));
    }
}
