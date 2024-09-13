package bestie.task;

/**
 * Creates a new todo task.
 */
public class Todo extends Task {

    /**
     * Creates a new todo task.
     *
     * @param description Description of the task.
     * @throws IllegalArgumentException If task description is empty.
     */
    public Todo(String description, Priority priority) throws IllegalArgumentException {
        super(description, priority);
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
        // Stores whether task has been completed, so that task can be correctly interpreted from stored file
        String storeCompleted = "";

        if (this.isDone) {
            storeCompleted = "1";
        } else {
            storeCompleted = "0";
        }
        // Store format: T | 1 | tutorial | HIGH
        return "T | " + storeCompleted + " | " + this.description + " | " + this.priority;
    }

    /**
     * Formats the task to be displayed on the console.
     *
     * @return String representation of the todo task on the console.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + ", priority: " + this.priority.toString();
    }
}
