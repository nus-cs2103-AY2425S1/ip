package astor.command;

import java.util.List;

import astor.Storage;
import astor.TaskList;
import astor.Ui;

/**
 * Represents the command to find for tasks that matches a specific task description.
 */
public class FindCommand extends Command {
    private String info;

    public FindCommand(String info) {
        this.info = info;
    }

    /**
     * Looks for tasks within the taskList and displays the string obtained from the search.
     *
     * @param taskList the list of tasks
     * @param ui manages the interaction with the user
     * @param storage stores the data
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String keyword = info.substring(4).trim();
        String tasks = taskList.matchesDescription(keyword);
        String output = "Here are the matching tasks in your list:" + tasks;
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
