package command;

import tasklist.TaskList;
import tasks.Task;
import tasks.ToDo;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class AddToDoCommand extends Command {
    /** The task to be added to the task list. */
    protected Task task;

    /**
     * Constructs an AddToDoCommand with the specified description.
     *
     * @param desc The description of the to-do task.
     */
    public AddToDoCommand(String desc) {
        task = new ToDo(desc);
    }

    /**
     * Executes the command by adding the to-do task to the task list and
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
        sb.append("  " + task);
        sb.append("Now you have " + tasklist.size() + " tasks in the list.\n");

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
