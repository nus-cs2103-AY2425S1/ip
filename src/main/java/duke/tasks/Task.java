package duke.tasks;

/**
 * This class represents a task.
 * It contains the name of the task.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a `Task` object with the specified name.
     *
     * @param name The name of the deadline.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Converts the task's information into a specific data format suitable for storage in a file.
     *
     * @return A string in the format to be stored in TaskDataBase.
     */
    public String toDataFormat() {
        if (isDone) {
            return "|1|" + name;
        } else {
            return "|0|" + name;
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + name;
    }

    public String getName() {
        return this.name;
    }
}
