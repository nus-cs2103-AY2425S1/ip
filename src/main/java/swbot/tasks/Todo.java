package swbot.tasks;

/**
 * A type of task that the user can add to the todo list.
 * A todo task has no start date or end date and only has a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
