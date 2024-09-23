package killua.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import killua.task.Deadline;
import killua.task.Event;
import killua.task.Task;


/**
 * Represents a list of tasks in the Killua task manager.
 * It provides methods to add, delete, mark, and unmark tasks,
 * as well as to retrieve tasks based on specific criteria.
 */
public class TaskList {
    private static final String INVALID_TASK_INDEX_MESSAGE = "You don't have this task!";
    private static final String ALREADY_MARK_MESSAGE = "You've already marked this task, stop it!";
    private static final String ALREADY_UNMARK_MESSAGE = "You can't unmark an unmarked task!";
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks An ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws KilluaException invalid index is given.
     */
    public void deleteTask(int index) throws KilluaException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new KilluaException(INVALID_TASK_INDEX_MESSAGE);
        }
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to be marked as done.
     * @throws KilluaException invalid index is given.
     */
    public void markTaskDone(int index) throws KilluaException {
        try {
            Task task = tasks.get(index);
            if (Objects.equals(task.getStatusIcon(), "X")) {
                throw new KilluaException(ALREADY_MARK_MESSAGE);
            }
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new KilluaException(INVALID_TASK_INDEX_MESSAGE);
        }
    }

    /**
     * Unmarks a task as not done yet at the specified index.
     *
     * @param index The index of the task to be unmarked.
     * @throws KilluaException invalid index is given.
     */
    public void unmarkTask(int index) throws KilluaException {
        try {
            Task task = tasks.get(index);
            if (Objects.equals(task.getStatusIcon(), " ")) {
                throw new KilluaException(ALREADY_UNMARK_MESSAGE);
            }
            task.unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new KilluaException(INVALID_TASK_INDEX_MESSAGE);
        }
    }

    /**
     * Returns a TaskList containing tasks that are scheduled for a specific date.
     *
     * @param date The date for which tasks are to be retrieved.
     * @return A TaskList containing tasks on the specified date.
     */
    public TaskList getTasksOnDate(LocalDate date) {
        TaskList tasksOnDate = new TaskList();
        tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline deadline) {
                        return deadline.getDate().equals(date);
                    } else if (task instanceof Event event) {
                        return !event.getStartDate().isAfter(date) && !event.getEndDate().isBefore(date);
                    }
                    return false;
                })
                .forEach(tasksOnDate::addTask);

        return tasksOnDate;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Prints all tasks in the task list with their respective index.
     */
    public String getTasksString() {
        return tasks.stream()
                .map(task -> String.format("%d.%s", tasks.indexOf(task) + 1, task))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
