package sage.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a collection of tasks in the task management application
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list
     * Initialises the tasks list to an empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with an initial list of tasks
     *
     * @param tasks A List of Task objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task to the task list
     *
     * @param task The task object to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on its index
     *
     * @param index The index of the task to be removed
     * @return The Task object that was removed from the list
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a list of all tasks in the task list
     *
     * @return A List of all Task objects in the task list
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Searches for tasks containing the specified keyword in the description
     *
     * @param keyword The keyword to search for
     * @return A list of tasks that contain the keyword
     */
    public TaskList searchTasks(String keyword) {
        ArrayList<Task> filtered = tasks.stream()
                .filter(task -> task.containsKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(filtered);
    }

    public List<Task> getTaskForDate(LocalDate date) {
        List<Task> tasksForDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getDeadline().toLocalDate().equals(date)) {
                    tasksForDate.add(task);
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.getFrom().toLocalDate().equals(date) || eventTask.getTo().toLocalDate().equals(date)) {
                    tasksForDate.add(task);
                }
            }
        }
        return tasksForDate;
    }
}
