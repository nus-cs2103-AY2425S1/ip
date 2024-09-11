package task;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param tasks The list of tasks to be added to the task list.
     */

    public TaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Method to add a task to the task list.
     * @param task The task to be added to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Method to get a task from the task list.
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Method to remove a task from the task list.
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Method to mark a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).setIsDone(true);
    }

    /**
     * Method to mark a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        tasks.get(index).setIsDone(false);
    }

    /**
     * Method to get the size of the task list.
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    public void sort() {
        tasks.sort(null);
    }

    public TaskList findTasks(String s) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.contains(s)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}