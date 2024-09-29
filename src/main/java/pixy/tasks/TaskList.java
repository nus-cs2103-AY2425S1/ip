package pixy.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the list of tasks inputted by the user.
 */
public class TaskList {

    /** List of Tasks */
    private List<Task> tasks;

    /**
     * Creates an empty ArrayList for the tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified tasks list.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of type Task.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param task The task to remove.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns a List of Tasks which match the description.
     *
     * @param description The description inputted by user.
     * @return List of Tasks having the specified description in their description.
     */
    public List<Task> find(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(description))
                .collect(Collectors.toList());
    }
    
    /**
     * Returns the task at the specified index of the TaskList.
     *
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the TaskList is empty or not.
     *
     * @return True if task list is empty, otherwise false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Sorts the TaskList by deadlines for Deadline tasks and event duration.
     */
    public void sort() {
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                // Determine order by task type
                if (t1 instanceof Deadlines && t2 instanceof Deadlines) {
                    // Both are deadlines, compare by due date and time
                    return ((Deadlines) t1).getDueDateTime().compareTo(((Deadlines) t2).getDueDateTime());
                } else if (t1 instanceof Event && t2 instanceof Event) {
                    // Both are events, compare by start time
                    return ((Event) t1).getFrom().compareTo(((Event) t2).getFrom());
                } else if (t1 instanceof Deadlines) {
                    // Deadlines come before other types
                    return -1;
                } else if (t2 instanceof Deadlines) {
                    // Other types come after deadlines
                    return 1;
                } else if (t1 instanceof Event) {
                    // Events come before todos
                    return -1;
                } else if (t2 instanceof Event) {
                    // Todos come after events
                    return 1;
                } else {
                    // Both are ToDos, no need to compare
                    return 0;
                }
            }
        };


        // Sort the tasks using the comparator
        Collections.sort(tasks, comparator);
    }
}
