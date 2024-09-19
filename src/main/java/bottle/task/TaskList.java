package bottle.task;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a list of tasks, allowing operations to manage the collection of tasks.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    protected ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the specified list of tasks.
     *
     * @param taskList the initial list of tasks
     * @throws IllegalArgumentException if taskList is null
     */
    public TaskList(ArrayList<Task> taskList) {
        if (taskList == null) {
            throw new IllegalArgumentException("taskList shouldn't be null!");
        }
        this.taskList = taskList;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     * @throws IllegalArgumentException if the task is null
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task cannot be null!");
        }
        taskList.add(task);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index the index of the task to be removed
     * @return the removed task
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    /**
     * Returns a string representation of all tasks in the list.
     * Each task is prefixed with its index (1-based).
     *
     * @return a string representing the list of tasks
     */
    @Override
    public String toString() {
        if (taskList == null) {
            throw new IllegalStateException("Task List shouldn't be null");
        }
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int idx = i + 1;
            tasks.append(idx).append(". ").append(taskList.get(i)).append("\n");
        }
        return tasks.toString();
    }

    /**
     * Detect anomalies array list.
     *
     * @return the array list
     */
    public TaskList detectAnomalies() {
        ArrayList<Event> eventList = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof Event) {
                eventList.add((Event) t);
            }
        }
        eventList.sort(Comparator.comparing(e -> e.from));
        ArrayList<Event> clashList = new ArrayList<>();
        for (int i = 0; i < eventList.size() - 1; i++) {
            Event prev = eventList.get(i);
            Event curr = eventList.get(i + 1);
            if (!prev.to.isBefore(curr.from)) {
                clashList.add(prev);
                clashList.add(curr);
            }
        }
        return new TaskList(new ArrayList<>(clashList.stream().distinct().toList()));
    }
}
