package mryapper.task;

/**
 * Contains all common functions and properties for tasks created by the ChatBot.
 * All tasks have a description and can be marked as done.
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Creates a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Checks whether the task has this given field and edit it.
     *
     * @param field The field to edit.
     * @param newString The string to update the field to.
     * @return The task with updated fields.
     */
    public abstract Task edit(TaskField field, String newString);

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns 1 if the task is done and 0 otherwise.
     *
     * @return 1 or 0 based on whether the task is done.
     */
    public int getStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a status icon to show whether the task is done.
     *
     * @return "X" if the task is done and " " if the task isn't.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }


    /**
     * Checks whether the task description has all keywords in the search input.
     *
     * @param searchInput The search input from the user.
     * @return Whether all keywords are contained in the task description.
     */
    public boolean hasKeywords(String searchInput) {
        String[] searchKeywords = searchInput.trim().split("\\s+");
        String[] descriptionKeywords = this.description.trim().split("\\s+");

        for (String word : searchKeywords) {
            boolean isKeywordPresent = checkArrayForKeyword(word, descriptionKeywords);
            if (!isKeywordPresent) {
                return false;
            }
        }
        return true;
    }

    private boolean checkArrayForKeyword(String keyword, String[] arr) {
        boolean hasKeyword = false;
        for (String word : arr) {
            if (keyword.equalsIgnoreCase(word)) {
                hasKeyword = true;
                break;
            }
        }
        return hasKeyword;
    }

    /**
     * Returns the string following the format of how the task is stored in the data file.
     *
     * @return The string to be written when storing the task in data text file.
     */
    public abstract String getDataString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
