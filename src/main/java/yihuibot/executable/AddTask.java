package yihuibot.executable;

import yihuibot.task.Task;
import yihuibot.task.TaskList;

/**
 * An executable for adding tasks.
 *
 * @author Toh Yi Hui A0259080A
 */
public class AddTask extends TaskModifier {
    private String output;
    private Task task;

    /**
     * Constructor for a new AddTask executable.
     *
     * @param task the task to be added.
     */
    public AddTask(Task task) {
        super();
        this.task = task;
    }

    /**
     * Constructor for a new AddTask executable.
     *
     * @param tasks the task list to add the new task into.
     * @param task the task to be added.
     */
    public AddTask(TaskList tasks, Task task) {
        super(tasks);
        this.task = task;
    }

    /**
     * Add the task to the list of tasks.
     *
     * @return false.
     * @throws NullPointerException when TaskList is null.
     */
    @Override
    public boolean execute() throws NullPointerException {
        if (super.tasks == null) {
            throw new NullPointerException("TaskList cannot be null.");
        }

        super.tasks.add(task);
        output = "Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + super.tasks.size() + " task(s) in your list.";
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
