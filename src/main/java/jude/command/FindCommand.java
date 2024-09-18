package jude.command;

import jude.Storage;
import jude.TaskList;
import jude.Ui;

/**
 * Represents the command which contains the instruction of series of actions to find a particular keyword
 * from the existing Tasklist.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        setMessage("Here are the matching tasks in your list: \n" + tasklist.search(keyword));
        ui.showMessage(getMessage());
    }
}
