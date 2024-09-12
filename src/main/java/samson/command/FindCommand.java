package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.Task;
import samson.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The <code> FindCommand </code> class represents a command that finds tasks in the task list
 * that match a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a <code> FindCommand </code> with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim().toLowerCase();  // Trim and convert to lowercase for better matching
    }

    /**
     * Executes the command by finding and displaying tasks that match the keyword.
     *
     * @param taskList The list of tasks to search in.
     * @param ui       The UI object used to display the results to the user.
     * @param storage  The storage object (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))  // Case-insensitive matching
                .collect(Collectors.toList());

        return ui.showFindResults(matchingTasks);
    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false because the FindCommand does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
