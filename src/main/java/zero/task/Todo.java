package zero.task;

/**
 * The {@code Todo} class represents a task that needs to be done without any specific date or time.
 * It is a subclass of {@code Task} and inherits all of its properties and methods.
 * This class overrides methods to provide specific formatting for Todo tasks.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFormatted() {
        return "T," + this.isDone() + "," + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
