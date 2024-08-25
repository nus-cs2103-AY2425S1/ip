package yihuibot.executable;

import yihuibot.task.TaskList;

/**
 * An executable for listing out tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public class ListTask extends TaskModifier {
    private String output;

    /**
     * Constructor for a new ListTask executable.
     */
    public ListTask() {
        super();
    }

    /**
     * Constructor for a new ListTask executable.
     *
     * @param tasks the TaskList to read from.
     */
    public ListTask(TaskList tasks) {
        super(tasks);
    }

    /**
     * Output the list of tasks in a nice format.
     *
     * @return false.
     * @throws NullPointerException when TaskList is null.
     */
    @Override
    public boolean execute() throws NullPointerException {
        if (super.tasks == null) {
            throw new NullPointerException("TaskList cannot be null.");
        }

        if (super.tasks.size() == 0) {
            output = "You have no tasks in your list.";
        } else {
            output = "Here are the tasks in your list:\n" + super.tasks.toString();
        }
        return false;
    }

    /**
     * Return the output of the executable.
     *
     * @return the output of the exectuable.
     */
    @Override
    public String getOutput() {
        return output;
    }
}
