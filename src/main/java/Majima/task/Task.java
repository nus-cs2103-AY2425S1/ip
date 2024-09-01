package Majima.task;

public class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.UNDONE;
        this.type = type;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Helps to parse info from
     */
    public String toFileString() {
        return "";
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon of the task. "[X]" if marked, "[ ]" if unmarked.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return status.getIcon();
    }

    /**
     * Marks the status icon of the task.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Unmarks the status icon of the task.
     */
    public void markAsUndone() {
        this.status = TaskStatus.UNDONE;
    }

    public enum TaskStatus {
        DONE("[X]"),
        UNDONE("[ ]");

        private final String icon;

        TaskStatus(String icon) {
            this.icon = icon;
        }

        /**
         * Gets the status icon of the task.
         */
        public String getIcon() {
            return icon;
        }
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

}
