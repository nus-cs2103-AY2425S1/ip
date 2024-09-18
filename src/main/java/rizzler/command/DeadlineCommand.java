package rizzler.command;

import rizzler.Storage;
import rizzler.task.Deadline;
import rizzler.task.TaskLog;

/**
 * Represents the command to create a Deadline object and add it to the <code>taskLog</code>.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructor for a DeadlineCommand object. Also creates a <code>Deadline</code> object.
     * @param deadlineDesc Text description of the task.
     * @param deadlineTime Date of the deadline in <code>YYYY-MM-DD</code> format.
     */
    public DeadlineCommand(String deadlineDesc, String deadlineTime) {
        super();
        this.deadline = new Deadline(deadlineDesc, deadlineTime);
    }

    /**
     * Executes the necessary actions by adding the Deadline object to <code>taskLog</code>, and storing
     * the updated <code>taskLog</code> with <code>storage</code>.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Multiple lines of output to be printed in response to creation of a new Deadline.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        taskLog.addTask(deadline);
        storage.storeTasks(taskLog);
        return createConfirmationMessage(taskLog.getNumTasks());
    }

    private String[] createConfirmationMessage(int newNumTasks) {
        return new String[] {"certainly, i'll keep track of this deadline for you ;)",
                "\t" + deadline,
                "now we have " + newNumTasks + " tasks to work on."};
    }
}
