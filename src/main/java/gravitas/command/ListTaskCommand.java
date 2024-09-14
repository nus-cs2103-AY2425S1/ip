package gravitas.command;

import gravitas.exception.GravitasException;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListTaskCommand extends Command {
    /**
     * Constructor for ListTaskCommand.
     */
    public ListTaskCommand() {
    }

    /**
     * Executes the command to list all tasks in the task list.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws GravitasException {
        return taskList.printTaskList();
    }
}
