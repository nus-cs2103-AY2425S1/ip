package Gary.command;

import java.util.ArrayList;

import Gary.Storage;
import Gary.TaskList;
import Gary.Ui;
import Gary.task.Task;


/**
 * The {@code FindCommand} class represents a command to find tasks containing a specified keyword.
 * It extends the {@code Command} class and implements the behavior for searching tasks by keyword.
 */
public class FindCommand extends Command {

    // The keyword to search for in task descriptions
    private String keyword;

    /**
     * Constructs a {@code FindCommand} object with the specified keyword to search for.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, which searches for tasks containing the specified keyword
     * in the {@code TaskList} and displays the matching tasks through {@code Ui}.
     *
     * @param taskList The {@code TaskList} object containing tasks to be searched.
     * @param ui The {@code Ui} object for user interaction, used to display messages.
     * @param storage The {@code Storage} object for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showError("No matching tasks found.");
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }

    /**
     * Indicates that this command is not a "bye" command.
     *
     * @return {@code false} as this is not a "bye" command.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
