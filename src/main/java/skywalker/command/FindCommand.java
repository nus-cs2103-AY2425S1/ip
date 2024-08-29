package skywalker.command;

import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Exceutes the find command, which finds the task that fulfills
     * the matching and print it out.
     *
     * @param tasks   The task list on which the command will operate.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save or load the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i).toString());
            }
        }
    }

}
