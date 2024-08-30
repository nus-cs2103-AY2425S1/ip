package julie.task;

/**
 * A class that represents a task to be done, with no time constraints
 */
public class ToDo extends Task {
    /**
     * Public constructor for ToDo.
     * @param s The string description for the Todo.
     */
    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
    @Override
    public String toStorageString() {
        return String.format("T | %s",this.taskString);
    }
}
