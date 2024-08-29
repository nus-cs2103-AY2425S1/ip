package darwin.command;

import darwin.exception.IllegalTaskNumberException;
import darwin.task.Task;
import darwin.task.TaskManager;
import darwin.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String CMD_WORD = "find";
    private static final String FIND_MSG = "Here are the matching tasks in your list:";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws IllegalTaskNumberException {
        String taskListStr = taskManager.findTasksStr(this.keyword);
        ui.send(FIND_MSG + "\n" + taskListStr);
    }
}
