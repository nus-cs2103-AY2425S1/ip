package yapper.task;

public class Todo extends Task {

    /*
     * Constructs a new Todo object with the specified description.
     */
    public Todo(String description) {
        super(description);
    }

    /*
     * Returns the string representation of the todo task for saving to a file.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }

    /*
     * Returns the string representation of the todo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
