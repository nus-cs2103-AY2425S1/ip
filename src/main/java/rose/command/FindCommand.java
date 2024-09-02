package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(this.keyword, ui);
    }
}
