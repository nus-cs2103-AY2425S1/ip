public class Todo extends Task {
    /**
     * Constructor for Todo class.
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStringSave() {
        int indicator = this.isDone ? 1 : 0;
        return "T | " + indicator + " | " + this.description;
    }

    /**
     * Returns a string representation of the task.
     * @return task description with status
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
