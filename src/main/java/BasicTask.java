public class BasicTask implements Task {
    /**
     * Whether the task is completed.
     */
    private boolean isCompleted;
    private String name;

    /**
     * Constructor for a new basic task.
     * @param name the name of the task
     */
    public BasicTask(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Mark the task as completed or not completed.
     * @param isCompleted whether the task is completed
     */
    public void mark(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Get the string representation of the task.
     * @return the string representation of the task.
     */
    public String toString() {
        String string;
        if (this.isCompleted) {
            string = "[X] ";
        } else {
            string = "[ ] ";
        }

        string = string + this.name;
        return string;
    }
}
