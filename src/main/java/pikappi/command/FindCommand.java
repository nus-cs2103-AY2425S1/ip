package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;
import pikappi.exception.PikappiException;
import pikappi.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        TaskList matchingTasks = tasks.findTask(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
