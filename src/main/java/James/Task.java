package james;

/**
 * Represents a task with a description and a completion status.
 * Provides methods to mark the task as done or not done, and to format the task for output or storage.
 */
abstract class Task {
    private String description;
    private Boolean isMarked;

    /**
     * Creates a new Task with the specified description and completion status.
     * Throws an exception if the description is empty.
     *
     * @param desc The description of the task.
     * @param mark The completion status of the task.
     * @throws MissingDescriptionException If the description is empty.
     */
    public Task(String desc, Boolean mark) throws MissingDescriptionException {
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("Looks like you left out the description of the task, please try again.");
        }
        this.description = desc;
        this.isMarked = mark;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isMarked = false;
    }

    /**
     * Returns a string representation of the task for display purposes.
     * The format is "[X] description" if the task is marked, or "[ ] description" if not.
     *
     * @return The formatted task string.
     */
    public String printTask() {
        String mark = isMarked ? "X" : " ";
        return String.format("[%s] %s", mark, description);
    }

    /**
     * Returns a string representation of the task for storage purposes.
     * The format is "markedStatus | description".
     *
     * @return The task in a format suitable for storage.
     */
    public String convertToFileFormat() {
        return String.format("%d | %s", isMarked ? 1 : 0, description);
    }

    /**
     * Checks if the specified substring is present in the task description.
     *
     * This method checks if the provided substring is contained within the task
     * description, ignoring case differences.
     *
     * @param subString The substring to search for in the task description.
     * @return true if the substring is found in the description; false otherwise.
     */
    public boolean matchSubstring(String subString) {
        return this.description.toLowerCase().contains(subString.toLowerCase());
    }

}
