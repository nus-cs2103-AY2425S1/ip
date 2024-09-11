package buddybot;

/**
 * Parent class for the Tasks To Do, Deadline and Event
 */
public class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType type;

    /**
     * Constructor for Task
     * @param description
     * @param type
     */
    public Task(String description, TaskType type) {
        assert description != null : "Description should not be empty!";
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        assert  type.equals(TaskType.TODO) || type.equals(TaskType.DEADLINE) || type.equals(TaskType.EVENT)
                : "The task type should only be Todo, Deadline or Event!";
        this.type = type;
    }

    /**
     * Return the status icon for each Task, to determine if they are done or not
     * @return
     */
    public String getStatusIcon() {
        return (status == TaskStatus.DONE ? "X" : " ");
    }

    /**
     * Mark the Task as done
     */
    public void mark() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Return a String of the Task with its status and description
     * @return
     */
    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Return a String for the Task in the format the file reader recognises
     * @return
     */
    public String toFile() {
        if (status == TaskStatus.DONE) {
            return "|X|" + description;
        } else {
            return  "|0|" + description;
        }
    }
}
