package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory
     * @throws LightException if an error occurs during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        return ui.beautifyMessage(TaskList.arrayToNumberedString(tasks));
    }
}
