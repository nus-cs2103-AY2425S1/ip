package rizzler.command;

import rizzler.Storage;
import rizzler.task.Deadline;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

/**
 * Represents the user's instruction to create a deadline.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_FORMAT = """
            Correct Usage:
            deadline {description} /by {time/date}
            Examples:
            deadline Finish Homework /by 2024-06-23
            deadline buy birthday present for dave /by this friday!!!""";
    private final Deadline deadline;

    /**
     * Constructs a DeadlineCommand object. Also creates a <code>Deadline</code> object.
     *
     * @param deadlineDesc Text description of the task.
     * @param deadlineTime Date of the deadline in <code>YYYY-MM-DD</code> format.
     *         Can also be in any other date or non-date format.
     */
    public DeadlineCommand(String deadlineDesc, String deadlineTime) throws RizzlerException {
        super();
        checkInputValidity(deadlineDesc, deadlineTime);
        this.deadline = new Deadline(deadlineDesc, deadlineTime);
    }

    /**
     * Executes the necessary actions in creating this Event object.
     * Adds the Deadline object to <code>taskLog</code>, and stores
     * the updated <code>taskLog</code> in <code>storage</code>.
     *
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Lines of output to be printed in response to creation of a new Deadline.
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

    private void checkInputValidity(String deadlineDesc, String deadlineTime) throws RizzlerException {
        if (deadlineDesc.isEmpty() || deadlineTime.isEmpty()) {
            throw new RizzlerException("missing argument");
        }
    }
}
