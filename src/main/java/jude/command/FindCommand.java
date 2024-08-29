package jude.command;

import jude.Storage;
import jude.TaskList;
import jude.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list: \n" + tasklist.search(keyword));
    }
}
