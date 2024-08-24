public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description
     * The task is initialized with the given description
     *
     * @param description The description of the task provided by the user
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo object.
     * The string includes a "[T]" prefix to indicate that this is a Todo task,
     * followed by the string representation provided by the Task superclass.
     *
     * @return A string in the format: "[T] <super.toString()>"
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }



}
