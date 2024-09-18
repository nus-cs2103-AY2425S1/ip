package skywalker.command;

import java.util.ArrayList;

import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;


/**
 * Customise the command type of find command.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            return "No matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.get(i).toString()).append("\n");
            }
            return result.toString();
        }
    }


}
