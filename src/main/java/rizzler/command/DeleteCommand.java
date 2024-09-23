package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

/**
 * Represents the user instruction to delete a task from the <code>taskLog</code>.
 */
public class DeleteCommand extends Command {
    protected static final String COMMAND_OVERVIEW = "delete: deletes a task from the log permanently.";
    protected static final String[] COMMAND_FORMAT = new String[] {
            "Usage: ",
            "\"delete {task ID number}\"",
            "Examples: ",
            "\"delete 3\"",
            "\"delete 1\""};
    private final int taskToDelete;


    /**
     * Constructor for a <code>DeleteCommand</code> object.
     * @param commandToDelete Number representing the task to be deleted under <code>list</code>
     */
    public DeleteCommand(int commandToDelete) {
        super();
        this.taskToDelete = commandToDelete;
    }

    /**
     * Deletes the command number given from the taskLog and storage. If the number given
     * does not correspond to any task, nothing is done and an error message is printed.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Lines to be printed in response to the delete command for user verification.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        try {
            Task deletedTask = taskLog.deleteTask(taskToDelete);
            storage.storeTasks(taskLog);
            return new String[] {"sure hon, i'll remove this task from the list.",
                    "\t" + deletedTask,
                    "now we have " + taskLog.getNumTasks() + " tasks left to work on."};
        } catch (RizzlerException e) {
            return new String[]{e.getMessage()};
        }
    }
}
