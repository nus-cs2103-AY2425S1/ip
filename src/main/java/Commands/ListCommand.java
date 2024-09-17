package Commands;

import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        return Ui.listDisplay(TaskList.getList());
    }
}
