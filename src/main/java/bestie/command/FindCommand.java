package bestie.command;

import bestie.Storage;
import bestie.task.Task;
import bestie.TaskList;
import bestie.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    /**
     * Finds all task with a matching keyword and displays them on the console, in a list format.
     *
     * @param keyword The keyword that the user wants to use to find specific tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);
        ui.showFoundTasks(matchingTasks);
    }

}
