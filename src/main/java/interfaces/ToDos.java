package interfaces;

/**
 * Represents a To-Do task, which is a specific type of Task without additional attributes.
 * This class extends the Task class and provides functionality specific to To-Do items.
 */
public class ToDos extends Task {

    /**
     * Constructs a new ToDo task.
     *
     * @param description The description of the ToDo task. The "todo " prefix will be removed if present.
     */
    public ToDos(String description) {
        super(description.replace("todo ", ""));
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string in the format "[T][x] description (#tag)" where x is replaced with " " if the task is not done.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task suitable for saving to a file.
     *
     * @return A string in the format "todo description | isDone | tag"
     */
    @Override
    public String loadString() {
        return "todo " + this.description + " | " + this.isDone + " | " + this.tag;
    }
}