package executable;

import task.TaskList;

import exception.executable.ExecutableException;

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
     * @param tasks the TaskList to modify.
     * @param idx the 1-index of the tasks to mark as incomplete.
     */
    public UnmarkTask(TaskList tasks, int idx) {
        super(tasks);
        this.idx = idx;
    }

    /**
     * Mark the task at idx as incomplete.
     *
     * @return false.
     * @throws NullPointerException when TaskList is null.
     * @throws ExecutableException when given index is out of bounds of ArrayList.
     */
    @Override
    public boolean execute() throws NullPointerException, ExecutableException {
        if (super.tasks == null) {
            throw new NullPointerException("TaskList cannot be null.");
        }

        try {
            if (super.tasks.markIncomplete(idx - 1)) {
                output = "Ok. I've marked this task as not done:\n" + super.tasks.get(idx - 1).toString();
            } else {
                output = "Task " + idx + " is not complete.";
            }
        } catch (IndexOutOfBoundsException e) {
            String message = idx + " index out of bounds of task list of size " + super.tasks.size() + ".";
            throw new ExecutableException(message);
        }
        return false;
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
