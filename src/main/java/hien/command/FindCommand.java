package hien.command;

import java.util.ArrayList;
import java.util.List;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Task;
import hien.ui.UI;

/**
 * Represents a command to find tasks based on a keyword.
 * This command searches for tasks whose descriptions contain the given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @param isExit A boolean indicating whether this command should exit the program.
     */
    public FindCommand(String keyword, boolean isExit) {
        super(isExit);
        this.keyword = keyword.trim().toLowerCase();
    }

    /**
     * Searches for tasks containing the keyword and displays the results.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI object used for displaying messages to the user.
     * @throws HienException If the search keyword is empty.
     */
    private void findTasks(TaskList tasks, UI ui) throws HienException {
        if (keyword.isEmpty()) {
            throw new HienException("☹ OOPS!!! The search keyword cannot be empty.");
        }

        ArrayList<Task> allTasks = tasks.getAllTasks();
        List<Task> matchingTasks = allTasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .toList();

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Executes the find command.
     * This method searches for tasks containing the keyword and displays the results.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The UI object used for displaying messages to the user.
     * @param storage The Storage object used for saving tasks (not used in this command).
     * @throws HienException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        findTasks(tasks, ui);
    }
}