package jeriel.command;

import jeriel.task.Task;
import jeriel.util.JerielException;
import jeriel.util.Storage;
import jeriel.util.TaskList;
import jeriel.util.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the keyword.
     *
     * @param tasks the task list to search from
     * @param ui the UI for output (not used in GUI mode)
     * @param storage the storage (not used in this command)
     * @return the result to be displayed in the GUI
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                result.append(String.format(" %d. %s\n", count++, task));
            }
        }
        return result.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
