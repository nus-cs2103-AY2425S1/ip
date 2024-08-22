import java.util.ArrayList;

/**
 * TaskList handles adding and listing of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks; // Array to store tasks
    private final UI ui;          // UI for handling user interface interactions

    /**
     * Constructs a new TaskList with a fixed size to store up to 100 tasks.
     *
     * @param ui UI class object needed for user interface interactions.
     */
    public TaskList(UI ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        this.ui.showMessage("Got it. I've added this task:\n  " + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to delete (1-based).
     */
    public void deleteTask(int index) {
        Task removedTask = tasks.remove(index - 1);
        this.ui.showMessage("Noted. I've removed this task:\n  " + removedTask +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Lists all the tasks currently stored in the task list.
     */
    public void listTasks() {
        StringBuilder tasksList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksList.append((i + 1)).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
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
        tasks.get(index - 1).setStatus(true);
        this.ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index - 1));
    }

    /**
     * Unmarks the specified task, setting it back to not done.
     *
     * @param index The index of the task to unmark (1-based index).
     */
    public void unmarkTask(int index) {
        tasks.get(index - 1).setStatus(false);
        this.ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index - 1));
    }
}
