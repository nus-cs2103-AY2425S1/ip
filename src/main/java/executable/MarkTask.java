package executable;

import task.TaskList;

import exception.executable.ExecutableException;

/**
 * An executable to mark tasks as done.
 *
 * @author Toh Yi Hui A0259080A
 */
public class MarkTask extends TaskModifier {
    private String output;
    private int idx;

    /**
     * Constructor for a new MarkTask executable.
     * 
     * @param idx the 1-index of the tasks to mark as complete.
     */
    public MarkTask(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Constructor for a new MarkTask executable.
     *
     * @param tasks the TaskList to modify.
     * @param idx the 1-index of the tasks to mark as complete.
     */
    public MarkTask(TaskList tasks, int idx) {
        super(tasks);
        this.idx = idx;
    }

    /**
     * Mark the task at idx as completed.
     *
     * @return false.
     * @throws NullPointerException when TaskList is null.
     * @throws ExecutableException when given index is out of bounds of TaskList.
     */
    @Override
    public boolean execute() throws NullPointerException, ExecutableException {
        if (super.tasks == null) {
            throw new NullPointerException("TaskList cannot be null.");
        }

        try {
            if (super.tasks.markComplete(idx - 1)) {
                output = "Nice! I've marked this task as done:\n" + super.tasks.get(idx - 1).toString();
            } else {
                output = "Task " + idx + " is already completed.";
            }
        } catch (IndexOutOfBoundsException e) {
            String message = idx + " index out bounds of task list of size " + super.tasks.size() + ".";
            throw new ExecutableException(message);
        }
        return false;
    }

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    @Override
    public String getOutput() {
        return output;
    }
}
