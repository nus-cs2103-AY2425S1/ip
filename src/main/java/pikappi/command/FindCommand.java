package pikappi.command;

import pikappi.Storage;
import pikappi.TaskList;
import pikappi.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTask(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
