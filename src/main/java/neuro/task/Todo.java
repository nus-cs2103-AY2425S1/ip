package neuro.task;

/**
 * The {@code Todo} class represents a todo task, which is a type of task with a description.
 * It extends the {@link Task} class, inheriting its properties and methods.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveData() {
        return "T | " + super.toSaveData();
    }
}
