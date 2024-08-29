package dgpt.task;

/**
 * Represents a task of type "ToDo" in the DGPT application.
 * <p>
 * A {@code ToDo} is a type of {@code Task} that does not have a specific deadline or time range.
 * It is characterized by its description, which is inherited from the {@code Task} class.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the specified description. By default, {@code isDone} is set to false.
     *
     * @param description The description of the {@code ToDo} task. This is a brief text that describes
     *                    what needs to be done.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}