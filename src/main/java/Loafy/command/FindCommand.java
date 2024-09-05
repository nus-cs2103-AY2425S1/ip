package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui) {
        ui.reply(taskList.find(this.keyword));
    }
}