package executable;

import java.util.ArrayList;

import task.Task;

/**
 * An executable to mark tasks as undone.
 *
 * @author Toh Yi Hui A0259080A
 */
public class UnmarkTask extends TaskModifier {
    private String output;
    private int idx;

    /**
     * Constructor for a new UnmarkTask executable.
     *
     * @param idx the 1-index of the tasks to mark as incomplete.
     */
    public UnmarkTask(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Constructor for a new UnmarkTask executable.
     *
     * @param tasks the tasks to modify.
     * @param idx the 1-index of the tasks to mark as incomplete.
     */
    public UnmarkTask(ArrayList<Task> tasks, int idx) {
        super(tasks);
        this.idx = idx;
    }

    /**
     * Mark the task at idx as incomplete.
     *
     * @return 0 normally, 2 if tasks == null.
     */
    @Override
    public int execute() {
        if (tasks == null) {
            output = "Task list cannot be null.";
            return 2;
        }

        try {
            Task task = super.tasks.get(idx - 1);
            if (task.markIncomplete()) {
                output = "Ok. I've marked this task as not done:\n" + task.toString();
            } else {
                output = "Task " + idx + " is not complete.";
            }
        } catch (IndexOutOfBoundsException e) {
            output = idx + " index out of bounds of task list of size " + super.tasks.size() + ".";
        }
        return 0;
    }

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    public String getOutput() {
        return output;
    }
}
