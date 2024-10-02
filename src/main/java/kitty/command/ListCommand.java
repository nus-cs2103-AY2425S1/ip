package kitty.command;

import kitty.TaskList;
import kitty.Ui;

public class ListCommand extends Command {
    public ListCommand(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    @Override
    public String run() {
        return ui.showTaskList(tasks);
    }
}
