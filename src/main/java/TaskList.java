/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    private final String[] tasks; // Array to store tasks
    private int taskCount;        // Number of tasks added

    /**
     * Constructs a new TaskList with a fixed size to store up to 100 tasks.
     */
    public TaskList() {
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + task);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Task list is full. Cannot add more tasks.");
        }
    }

    /**
     * Lists all the tasks currently stored in the task list.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }
}