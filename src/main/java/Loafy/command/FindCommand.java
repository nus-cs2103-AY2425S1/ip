package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList taskList, Ui ui) {
        return taskList.find(this.keyword);
    }
}