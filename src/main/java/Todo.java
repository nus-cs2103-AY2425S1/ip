/**
 * The {@code Todo} class represents a task that has a description and can be marked as done or not done.
 * It extends the {@code Task} class and provides specific implementations for the {@code toString} and {@code toData} methods.
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
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("TODO | %s | %s\n", status, this.description);
    }

}
