package fridayproject;

/*
 * Represents a todo task.
 * A todo task has a description.
 */
public class Todo extends Tasks{

    /*
     * Constructor for a todo task.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /*
     * Returns the string representation of the task.
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /*
     * Returns the string representation of the task to be saved in the file.
     */
    @Override 
    public String toFileString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}