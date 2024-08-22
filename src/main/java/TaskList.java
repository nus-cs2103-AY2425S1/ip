/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    private final Task[] tasks; // Array to store tasks
    private int taskCount;        // Number of tasks added
    private final UI ui;          // UI for handling user interface interactions

    /**
     * Constructs a new TaskList with a fixed size to store up to 100 tasks.
     *
     * @param ui UI class object needed for user interface interactions.
     */
    public TaskList(UI ui) {
        this.tasks = new Task[100];
        this.taskCount = 0;
        this.ui = ui;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            this.ui.showMessage("Got it. I've added this task:\n  " + task +
                    "\nNow you have " + taskCount + " tasks in the list.");
        } else {
            this.ui.showMessage("Task list is full. Cannot add more tasks.");
        }
    }

    /**
     * Lists all the tasks currently stored in the task list.
     */
    public void listTasks() {
        StringBuilder tasksList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            tasksList.append((i + 1)).append(". ").append(tasks[i]);
            if (i < taskCount - 1) {
                tasksList.append("\n"); // Add newline only if it is not the last task
            }
        }
        this.ui.showMessage(tasksList.toString());
    }

    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to mark as done (1-based index).
     */
    public void markTask(int index) {
        if (index <= 0 || index > taskCount) {
            ui.showMessage("Invalid task number.");
            return;
        }

        tasks[index - 1].setStatus(true);
        this.ui.showMessage("Nice! I've marked this task as done:\n  " + tasks[index - 1]);
    }

    /**
     * Unmarks the specified task, setting it back to not done.
     *
     * @param index The index of the task to unmark (1-based index).
     */
    public void unmarkTask(int index) {
        if (index <= 0 || index > taskCount) {
            ui.showMessage("Invalid task number.");
            return;
        }

        tasks[index - 1].setStatus(false);
        this.ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks[index - 1]);
    }
}
