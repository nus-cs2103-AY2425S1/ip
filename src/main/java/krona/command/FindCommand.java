package krona.command;

import krona.task.Task;
import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;
import krona.exception.KronaException;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding tasks that contain the keyword,
     * displaying them through the UI.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i).toString());
            }
        }
    }
}