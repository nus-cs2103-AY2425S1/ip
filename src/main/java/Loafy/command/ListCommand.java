package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui) {
        ui.reply(taskList.view());
    }
}
