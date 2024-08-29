package topaz.command;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.ui.Ui;

public class Command {
    protected boolean isExit;
    protected String keyword;

    public Command(String keyword) {
        this.isExit = false;
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    public boolean isExit() {
        return isExit;
    }
}
