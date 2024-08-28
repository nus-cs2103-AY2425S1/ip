package demurebot.command;

import demurebot.task.TaskList;
import demurebot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        if (list.getSize() == 0) {
            ui.displayEmptyList();
        } else {
            ui.displayList(list);
        }
    }
}
