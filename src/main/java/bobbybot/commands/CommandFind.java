package bobbybot.commands;

import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

public class CommandFind extends Command {
    private final String keyword;

    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTaskArray = tasks.findTasks(keyword);
        ui.listMatchingTasks(matchingTaskArray);
    }

}
