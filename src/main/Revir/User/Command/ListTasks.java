package Revir.User.Command;

import Revir.Tasks.TaskList;
import Revir.User.Ui;

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
