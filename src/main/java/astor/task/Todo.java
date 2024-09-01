package astor.task;

/**
 * Represents a to-do task.
 *
 * This class extends the Task class and is used to represent tasks that do not have
 * any specific deadline or time associated with them.
 */
public class Todo extends Task {
    public Todo(String taskInfo) {
        super(taskInfo);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * The string representation includes the task type, completion status, and task description.
     * The status is shown as "[X]" if the task is done, and "[ ]" if it is not.
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        String s = "[T] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo();
        return s;
    }

    /**
     * Returns a string description of the to-do task data for storage.
     *
     * The string includes the task type, completion status, and task description.
     *
     * @return a string description of the to-do task data
     */
    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "T | " + i + " | " + this.getTaskInfo();
    }
}
