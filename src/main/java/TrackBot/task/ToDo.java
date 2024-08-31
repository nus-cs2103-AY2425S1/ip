package TrackBot.task;

/**
 * ToDo task with only a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param desc The description of the ToDo task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toStorageFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + desc;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
