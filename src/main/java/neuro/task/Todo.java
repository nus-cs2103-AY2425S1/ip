package neuro.task;

/**
 * The {@code Todo} class represents a todo task, which is a type of task with a description.
 * It extends the {@link Task} class, inheriting its properties and methods.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n    " + super.tagsToString();
    }

    @Override
    public String toSaveData() {
        return "T | " + super.toSaveData();
    }
}
