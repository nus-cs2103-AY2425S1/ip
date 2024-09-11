package kitty.command;

import kitty.TaskList;
import kitty.Ui;

public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        this.keyword = keyword;
    }

    @Override
    public String run() {
        String msg = tasks.findTask(keyword);
        return ui.showFindTask(msg);
    }
}
