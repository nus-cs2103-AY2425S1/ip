package command;

import tasklist.TaskList;
import tasks.Deadline;
import tasks.DeadlineException;
import tasks.Task;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    /** The task to be added to the task list. */
    protected Task task;

    /**
     * Constructs an AddDeadlineCommand with the specified description and date.
     *
     * @param desc The description of the deadline task.
     * @param date The due date of the deadline task in the format "yyyy-mm-dd".
     * @throws DeadlineException If the date format is invalid.
     */
    public AddDeadlineCommand(String desc, String date) throws DeadlineException {
        task = new Deadline(desc, date);

    }

    /**
     * Executes the command by adding the deadline task to the task list and
     * displaying the appropriate messages in the command line interface.
     *
     * @param tasklist The TaskList where the task will be added.
     * @return Confirmation of the task added.
     */
    @Override
    public String execute(TaskList tasklist) {
        tasklist.addTask(task);

        StringBuilder sb = new StringBuilder();

        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + tasklist.size() + " tasks in the list.");

        return sb.toString();
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
