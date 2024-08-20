/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    private final String[] tasks; // Array to store tasks
    private int taskCount;        // Number of tasks added
    private final UI ui;          // UI for handling user interface interactions

    /**
     * Constructs a new TaskList with a fixed size to store up to 100 tasks.
     *
     * @param ui UI class object needed for user interface interactions.
     */
    public TaskList(UI ui) {
        this.tasks = new String[100];
        this.taskCount = 0;
        this.ui = ui;
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
            this.ui.showMessage("added: " + task);
        } else {
            this.ui.showMessage("Task list is full. Cannot add more tasks.");
        }
    }

    /**
     * Lists all the tasks currently stored in the task list.
     */
    public void listTasks() {
        StringBuilder tasksList = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            tasksList.append((i + 1)).append(". ").append(tasks[i]);
            if (i < taskCount - 1) {
                tasksList.append("\n"); // Add newline only if it is not the last task
            }
        }
        String allTasks = tasksList.toString();
        this.ui.showMessage(allTasks);
    }
}
