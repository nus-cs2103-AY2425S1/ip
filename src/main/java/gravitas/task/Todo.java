package gravitas.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }

    @Override
    public String getDescription() {
        return super.description;
    }

    @Override
    public String formatData() {
        String mark = this.isDone ? "1" : "0";
        return (this.eventType + " | " + mark + " | " + this.description + "\n");
    }
}
