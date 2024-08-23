public class Task {
    private final String description;

    /**
     * Constructor for Task class, task initialized with user inputted description
     *
     * @param description description of the task command inputted by the user
     */
    public Task (String description) {
        this.description = description;
    }

    /**
     * Returns the description of the task
     */
    public String getDescription() {
        return this.description;
    }
}
