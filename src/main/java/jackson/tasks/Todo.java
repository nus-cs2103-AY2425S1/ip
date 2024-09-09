package jackson.tasks;

import jackson.utils.CustomDateTime;

/**
 * Todo class containing name.
 */
public class Todo extends Task {

    /**
     * Constructs Todo task instance
     * @param name String name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public CustomDateTime getStartDateTime() {
        return null;
    }

    @Override
    public CustomDateTime getEndDateTime() {
        return null;
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}
