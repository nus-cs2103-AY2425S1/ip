package tudee.command;

import tudee.TudeeException;
import tudee.storage.Storage;
import tudee.task.Task;
import tudee.task.TaskList;
import tudee.ui.Ui;

/**
 * Represents a command to find tasks in the task list that contain a specified keyword.
 * The command searches through all tasks and lists those that match the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by finding tasks which descriptions contain the keyword.
     * If tasks matching the keyword are found, they will be printed using the UI.
     * If no matching tasks are found, a TudeeException is thrown.
     *
     * @param tasks The task list to search through.
     * @param ui The UI to display the tasks.
     * @param storage The storage object, though not used in this command.
     * @throws TudeeException If no tasks contain the specified keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException {
        // Assert that tasks, ui and storage are not null.
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        TaskList matchingTasks = new TaskList();

        for (Task task: tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.numOfTasks() == 0) {
            throw new TudeeException("There is no such task with the keyword specified");
        }

        return ui.showMatchingTasks(matchingTasks);
    }
}
