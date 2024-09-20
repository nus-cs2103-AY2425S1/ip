package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;

/**
 * Represents the command to find for tasks that matches a specific task description.
 */
public class FindCommand extends Command {
    private final String info;

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
        assert taskList != null : "taskList must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";

        String[] keyword = info.substring(4).trim().split("\\s+");
        String tasks = taskList.matchesDescriptions(keyword);
        String output = "Here are the matching tasks in your list:" + tasks;
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
