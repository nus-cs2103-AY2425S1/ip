package shnoop.tasks;

/**
 * Encapsulates a task that does not have any time-related details.
 */
public class Todo extends Task {

    /**
     * Creates a Todo Task based on the given description.
     *
     * @param description Details of the Todo Task.
     */
    public Todo(String description) {
        super(description.substring(5, description.length()));
    }

    /**
     * Creates a Todo Task based on the given description, but also mark it as done.
     * Typically used by FileWriter / FileReader when loading from Storage and adding to TaskList.
     *
     * @param description Details of the Todo Task.
     * @param done True if the Todo Task created should be marked as done.
     */
    public Todo(String description, boolean done) {
        super(description);
        if (done) {
            this.markTask();
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toUniqueFileString() {
        String s = super.toUniqueFileString();
        s += "001"; // Unique identifier for Todo Tasktype
        s += super.description;
        return s;
    }
}
