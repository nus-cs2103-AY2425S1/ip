/**
 * The TaskList class represents list of tasks input by user.
 * As per requirements it can store up to 100 tasks.
 */
public class TaskList {
    private String[] tasks;
    private int taskCount;

    /**
     * Constructs a new TaskList with an Array of 100 tasks.
     * Initializes the task count to zero.
     */
    public TaskList() {
        this.tasks = new String[100];
        this.taskCount = 0;
    }
}