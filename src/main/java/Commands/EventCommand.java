package Commands;

import task.EventTask;
import task.TaskList;
import ui.Ui;

public class EventCommand extends GeneralTaskCommand {
    public EventCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        // split again after from
        // split again after to
        String[] firstSplit = this.cmd.split(" /from ", 2);
        String taskDes = firstSplit[0];
        String second = firstSplit[1];

        String[] secondSplit = second.split(" /to ", 2);
        assert secondSplit.length == 2 : "Event task should be properly formatted with '/from' and '/to'";
        String from = secondSplit[0];
        String to = secondSplit[1];

        EventTask tsk = new EventTask(taskDes, from, to);
        TaskList.addTask(tsk);
        return Ui.taskAddDescription(tsk);
    }

}
