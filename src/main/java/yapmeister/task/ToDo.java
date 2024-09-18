package yapmeister.task;

/**
 * Represents a Todo Task
 * @author BlazeChron
 */
public class ToDo extends Task {

    /**
     * Creates a Todo Task
     * @param taskName Name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String exportString() {
        String completedString;
        if (this.getIsCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("T|%s|%s", completedString, this.getTaskName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
