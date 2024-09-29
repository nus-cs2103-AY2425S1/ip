package Commands;

import task.TaskList;
import task.ToDoTask;
import ui.Ui;

public class ToDoCommand extends GeneralTaskCommand {

    public ToDoCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        ToDoTask tsk = new ToDoTask(this.cmd);
        TaskList.addTask(tsk);
        return Ui.taskAddDescription(tsk);
    }
}
