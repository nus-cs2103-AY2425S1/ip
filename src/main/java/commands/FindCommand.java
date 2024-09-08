package commands;

import java.util.List;

import skibidi.Command;
import skibidi.Ui;
import storage.Task;
import storage.TaskStorage;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.substring(5).trim();
    }

    /**
     * Executes the command to find tasks by keyword.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message to be displayed to the user.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        List<Task> matchingTasks = storage.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            return ui.outputMessage("No matching tasks found.");
        } else {
            StringBuilder str = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                str.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return ui.outputMessage(str.toString());
        }
    }
}
