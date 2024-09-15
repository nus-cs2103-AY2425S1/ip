package weeny.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.time.LocalTime;


/**
 * Manages a list of tasks, allowing addition, deletion, and status updates.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark.
     */
    public void markAsDone(int index) {
        tasks.get(index).setAsDone();
    }

    /**
     * Unmarks a task as not done by its index.
     *
     * @param index The index of the task to unmark.
     */
    public void markAsNotDone(int index) {
        tasks.get(index).setAsNotDone();
    }

    /**
     * Find tasks that contains keyword
     *
     * @param keyWord String that we are looking for in a task
     * @return ArrayList of tasks that contains keyWord
     */
    public List<Task> findTask(String keyWord) {
        List<Task> searchResult = getTasks().stream().filter(task -> task.containString(keyWord))
                .collect(Collectors.toList());
        return searchResult;
    }

    /**
     *
     */
    public List<Task> getSchedule(String date) {
        List<Task> schedule = getTimeSortedTasks().stream().filter(task -> task.containsDate(date)).collect(Collectors.toList());
        return schedule;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sorts list of tasks on a particular date by type then time
     *
     * @return List of sorted tasks
     */
    public List<Task> getTimeSortedTasks() {
        return tasks.stream()
                .sorted(Comparator
                        .comparingInt(task -> getTaskTypeOrder((Task) task))
                        .thenComparing(task -> {
                            if (task instanceof Deadline) {
                                return ((Deadline) task).getDueTime();
                            } else if (task instanceof Event) {
                                return ((Event) task).getStartTime();
                            } else {
                                return LocalTime.MAX;
                            }
                        }))
                .collect(Collectors.toList());
    }

    /**
     * Helper function to sort tasks
     *
     * @param task current task being iterated
     * @return integer value priority to sort
     */
    private int getTaskTypeOrder(Task task) {
        if (task instanceof Todo) {
            return 0;
        } else if (task instanceof Deadline) {
            return 1;
        } else if (task instanceof Event) {
            return 2;
        }
        return 3;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a task by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
