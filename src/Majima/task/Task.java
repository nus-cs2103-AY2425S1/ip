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

    /*
    This method should not be called upon and only exists to be overridden by the subclasses of Task.
     */
    public String toFileString() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return status.getIcon();
    }

    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

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
