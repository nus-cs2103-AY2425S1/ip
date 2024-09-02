package spiderman;

public class Todo extends Task {
    /**
     * Constructs a Todo object with the given description.
     * @param description The description of the Todo
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Formats the Todo's description and tags it with a T
     * to be saved into the txt file.
     * @return A string that contains the Todo's description and status.
     */
    @Override
    public String toTextFormat() {
        return "T|" + (super.isTaskDone() ? "T" : "F") + "|" + super.getDescription();
    }

    /**
     * Converts Todo's description and tags it with a T.
     * @return A string containing the description and status of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
