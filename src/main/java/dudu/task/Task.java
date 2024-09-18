package dudu.task;

/**
 * Represents an abstract class for a task
 */
public class Task {

    private boolean isMarked = false;

    private String description;

    /**
     * Constructs a Task with the specified description. By default, the task is uncompleted.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted() {
        this.isMarked = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markUncompleted() {
        this.isMarked = false;
    }

    /**
     * Returns if the task description contains any of the queries
     *
     * @param queries Queries to search the task description for
     * @return True if task description contains any of the queries else false
     */
    public boolean includes(String ... queries) {
        for (String query : queries) {
            if (this.description.toLowerCase().contains(query.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Formats the task into a string for storage, including the task type ("T" for to-do),
     * its completion status and description.
     *
     * @return The formatted string representation of the deadline task for storage.
     */
    public String formatString() {
        String markedString = this.isMarked ? "1" : "0";
        return String.format("%s | %s", markedString, this.description);
    }

    /**
     * Returns a string representation of the task, including its status
     * (marked or unmarked) and its description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String markedString = this.isMarked ? "[X]" : "[ ]";
        return String.format("%s %s", markedString, this.description);
    }

    /**
     * Compares this Task to another object for equality. Two tasks are considered
     * equal if they have the same description and completion status.
     *
     * @param object The object to compare this Task with.
     * @return true if the other object is a Task with the same description and status, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) object;
        boolean hasSameMarked = otherTask.isMarked == this.isMarked;
        boolean hasSameDescription = this.description.equals(otherTask.description);
        return hasSameMarked && hasSameDescription;
    }
}
