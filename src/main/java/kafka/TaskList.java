package kafka;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /** List storing the tasks */
    public final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks List of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Prints all tasks in the list.
     */
    public void printList() {
        if (tasks.isEmpty()) {
            return;
        }
        String listMessage = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            listMessage += "  " + (i + 1) + "." + t + "\n";
        }
        System.out.println(listMessage);
    }

    /**
     * Marks a task as done.
     *
     * @param t The task to be marked as done.
     */
    public void mark(Task t) {
        t.markAsDone();
    }

    /**
     * Unmarks a task as not done.
     *
     * @param t The task to be marked as not done.
     */
    public void unmark(Task t) {
        t.markAsNotDone();
    }

    /**
     * Deletes a task by its task number.
     *
     * @param taskNumber The task number to be deleted (1-based index).
     */
    public void delete(int taskNumber) {
        if (this.tasks.isEmpty()) {
            return;
        }
        this.tasks.remove(taskNumber - 1);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Finds tasks in the list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @return A new TaskList containing the found tasks.
     */
    public TaskList find(String keyword) {
        TaskList temp = new TaskList();
        for (Task t : tasks) {
            if (t.description.toLowerCase().contains(keyword)) {
                temp.addTask(t);
            }
        }
        return temp;
    }
}
