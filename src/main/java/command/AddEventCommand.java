package command;

import tasklist.TaskList;
import tasks.Event;
import tasks.EventException;
import tasks.Task;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command {
    /** The task to be added to the task list. */
    protected Task task;

    /**
     * Constructs an AddEventCommand with the specified description, start date, and end date.
     *
     * @param desc  The description of the event task.
     * @param start The start date of the event in the format "yyyy-mm-dd".
     * @param end   The end date of the event in the format "yyyy-mm-dd".
     * @throws EventException If the date format is invalid.
     */
    public AddEventCommand(String desc, String start, String end) throws EventException {
        task = new Event(desc, start, end);
    }

    /**
     * Executes the command by adding the event task to the task list and
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
