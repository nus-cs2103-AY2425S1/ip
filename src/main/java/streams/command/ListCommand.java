package streams.command;

import streams.exception.StreamsException;
import streams.task.TaskList;
import streams.util.Storage;
import streams.util.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks The task list to display.
     * @param ui The user interface to show the tasks.
     * @param storage The storage (not used in this command).
     * @throws StreamsException If there's an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        if (tasks.isEmpty()) {
            ui.showMessage("your task list is empty!!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }
}
