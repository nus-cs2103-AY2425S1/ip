package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.Task;
import Majima.task.TaskList;
import Majima.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand object, which when executed, produces a list of
     * tasks containing said keyword in the description and returns it to
     * the user Ui.
     *
     * @param keyword Keyword to check for in the current lists
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        List<Task> foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
