package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui) {
        ui.reply(tasks.view());
    }
}
