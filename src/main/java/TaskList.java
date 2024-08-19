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

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     */
    public void addTask(String task) {
        this.tasks[this.taskCount] = task;
        this.taskCount++;
        System.out.println("added: " + task);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            if (tasks[i] == null) {
                break;
            }
            result = result + i + "." + tasks[i] + "\n";
        }
        return result;
    }
}