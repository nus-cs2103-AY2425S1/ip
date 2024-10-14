package seedu.avo.tasks;

/**
 * Represents a task with no deadlines
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String formatData() {
        return String.format("T : %s", super.formatData());
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
