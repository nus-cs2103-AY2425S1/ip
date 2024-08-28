package User.Command;

import Tasks.TaskList;
import User.Ui;

public class ListTasks extends Command {
    public ListTasks() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        String list = taskList.list();
        ui.showResult("List:\n"+list);
    }
}
