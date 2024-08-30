package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying the list of tasks to the user.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}