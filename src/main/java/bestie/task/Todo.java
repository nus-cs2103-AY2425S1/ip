package bestie.task;

public class Todo extends Task {

    /**
     * Creates a new todo task.
     *
     * @param description Description of the task.
     * @throws IllegalArgumentException If task description is empty.
     */
    public Todo(String description) throws IllegalArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty!");
        }
    }

    /**
     * Formats the task in a consistent format to be saved in the bestie.txt file.
     * Allows for easier retrieval and converting tasks stored in bestie.txt into the TaskList.
     *
     * @return Formatted tasks to be saved in bestie.txt.
     */
    @Override
    public String toSaveFormat() {
        // stores whether task has been marked as done - 1 for marked and 0 for unmarked
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // store format: T | 1 | tutorial
        return "T | " + storeCompleted + " | " + this.description;
    }

    /**
     * Formats the task to be displayed on the console.
     *
     * @return String representation of the todo task on the console.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
