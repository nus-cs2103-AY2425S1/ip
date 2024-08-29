package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        ui.showFilteredTasks(tasks, keyword);
    }
}
