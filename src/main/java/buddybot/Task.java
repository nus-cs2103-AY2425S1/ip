package buddybot;

public class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getStatusIcon() {
        return (status == TaskStatus.DONE ? "X" : " ");
    }

    /**
     *
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFile() {
        if (status == TaskStatus.DONE) {
            return "|X|" + description;
        } else {
            return  "|0|" + description;
        }
    }
}
