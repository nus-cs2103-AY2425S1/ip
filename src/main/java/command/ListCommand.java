package command;

import task.TaskManager;
import ui.Ui;

public class ListCommand extends Command {
    public static final String CMD_WORD = "list";
    private static final String CHECK_LIST_STR = "Here are the tasks in your list:";

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        String msg = ListCommand.CHECK_LIST_STR + "\n" + taskManager.getTaskListStr();
        ui.send(msg);
    }
}
