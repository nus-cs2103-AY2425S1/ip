package task;

public class Task {

    protected String description;
    protected boolean isComplete;

    protected String input;

    /**
     * Constructs a Task with the specified description and input.
     *
     * @param description The description of the task.
     * @param input The input used to create the task.
     */

    public Task (String description, String input) {
        this.description = description;
        this.isComplete = false;
        this.input = input;
    }
    /**
     * Returns the original input used to create the task.
     *
     * @return The input string of the task.
     */
    public String getInput() {
        return this.input;
    }

    public String getDescription() { return this.description;}
    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String marked;
        if (isComplete) {
            marked = "[X] ";
        } else {
            marked = "[ ] ";
        }
        return marked + this.description;
    }
    /**
     * Marks the task as complete.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmark() {
        this.isComplete = false;
    }
}