package executable;

import java.util.ArrayList;

import task.Task;

/**
 * An executable for deleting tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public class DeleteTask extends TaskModifier {
    private String output;
    private int idx;

    /**
     * Constructor for a new DeleteTask executable.
     *
     * @param idx the 1-index of the tasks to delete.
     */
    public DeleteTask(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Constructor for a new DeleteTask executable.
     *
     * @param tasks the tasks to modify.
     * @param idx the 1-index of the tasks to delete.
     */
    public DeleteTask(ArrayList<Task> tasks, int idx) {
        super(tasks);
        this.idx = idx;
    }

    /**
     * Delete the task from list.
     *
     * @return NORMAL normally, ERROR if tasks == null.
     */
    @Override
    public Executable.exitCode execute() {
        if (super.tasks == null) {
            output = "Task list cannot be null.";
            return Executable.exitCode.ERROR;
        }

        try {
            ArrayList<Task> tasks = super.tasks;
            Task task = tasks.remove(idx - 1);
            output = "Noted. I've removed this task:\n" + task.toString()
                    + "\nNow you have " + tasks.size() + " task(s) in your list.";
        } catch (IndexOutOfBoundsException e) {
            output = idx + " index out bounds of task list of size " + super.tasks.size() + ".";
        }
        return Executable.exitCode.NORMAL;
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
