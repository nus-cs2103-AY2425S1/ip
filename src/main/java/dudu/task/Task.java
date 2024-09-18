package dudu.task;

/**
 * Represents a task.
 */
public class Task {

    private boolean isMarked = false;

    private String description;

    /**
     * Constructs a Task.
     *
     * @param description Task description.
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
     * Checks if the task description contains any of the queries.
     *
     * @param queries Queries to search the task description for.
     * @return True if task description contains any of the queries else false.
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
     * Returns task in storage format.
     */
    public String toStorageString() {
        String markedString = this.isMarked ? "1" : "0";
        return String.format("%s | %s", markedString, this.description);
    }

    /**
     * Returns task in display format.
     */
    @Override
    public String toString() {
        String markedString = this.isMarked ? "[X]" : "[ ]";
        return String.format("%s %s", markedString, this.description);
    }

    /**
     * Compares this object with another object.
     * Compares for same description and marked status.
     *
     * @param object Object to compare with.
     * @return True if object has the same fields else false.
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
