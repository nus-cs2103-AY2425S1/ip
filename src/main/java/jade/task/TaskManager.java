package jade.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * Manages a list of tasks.
 */
public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskManager object with the specified storage.
     * Loads tasks from the provided storage and initialises the task list.
     *
     * @param storage The storage handler to load and save tasks.
     */
    public TaskManager(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>(storage.loadTasks());
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The total number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Retrieves all tasks as an ArrayList.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert isValidTaskIndex(index) : "Invalid task index: " + index;
        return tasks.get(index);
    }

    /**
     * Checks if the given index is a valid task index.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Finds and returns all tasks that match a given keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks that match the specified keyword.
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
        return new ArrayList<>(matchingTasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert storage != null : "Storage should not be null";

        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Marks a task from the task list.
     *
     * @param index The index of the task to be marked.
     * @param isDone Whether the task has been marked.
     */
    public void markTask(int index, boolean isDone) {
        assert isValidTaskIndex(index) : "Invalid task index: " + index;
        assert storage != null : "Storage should not be null";

        if (isDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
        storage.saveTasks(tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @throws JadeException if index is invalid.
     */
    public void deleteTask(int index) throws JadeException {
        assert storage != null : "Storage should not be null";

        if (isValidTaskIndex(index)) {
            tasks.remove(index);
            storage.saveTasks(tasks);
        } else {
            throw new JadeException("Hmm, no such task. Try again.");
        }
    }

    /**
     * Sorts tasks alphabetically by description.
     */
    public void sortTasksAlphabetically() {
        tasks.sort(Comparator.comparing(Task::getDescription));
        storage.saveTasks(tasks);
    }

    /**
     * Sorts tasks by type.
     */
    public void sortTasksByTaskType() {
        tasks.sort(Comparator.comparing(this::getTaskType));
        storage.saveTasks(tasks);
    }

    /**
     * Sorts tasks by deadline, putting Deadline tasks first in ascending order
     * and leaving other tasks in their original order.
     */
    public void sortTasksByDeadline() {
        // Separate Deadline tasks from other tasks
        List<Task> deadlines = tasks.stream()
                .filter(task -> task instanceof Deadline)
                .sorted(Comparator.comparing(task -> ((Deadline) task).getBy()))
                .toList();

        List<Task> others = tasks.stream()
                .filter(task -> !(task instanceof Deadline))
                .toList();

        // Combine sorted Deadline tasks with other tasks
        tasks.clear();
        tasks.addAll(deadlines);
        tasks.addAll(others);

        storage.saveTasks(tasks);
    }

    /**
     * Sorts tasks by event start time, putting Event tasks first in ascending order
     * and leaving other tasks in their original order.
     */
    public void sortTasksByEvent() {
        // Separate Event tasks from other tasks
        List<Task> events = tasks.stream()
                .filter(task -> task instanceof Event)
                .sorted(Comparator.comparing(task -> ((Event) task).getFrom()))
                .toList();

        List<Task> others = tasks.stream()
                .filter(task -> !(task instanceof Event))
                .toList();

        // Combine sorted Event tasks with other tasks
        tasks.clear();
        tasks.addAll(events);
        tasks.addAll(others);

        storage.saveTasks(tasks);
    }

    /**
     * Maps a Task to its corresponding TaskType.
     *
     * @param task The task to be mapped.
     * @return The TaskType of the task.
     */
    private TaskType getTaskType(Task task) {
        if (task instanceof Todo) {
            return TaskType.TODO;
        } else if (task instanceof Deadline) {
            return TaskType.DEADLINE;
        } else if (task instanceof Event) {
            return TaskType.EVENT;
        } else {
            throw new IllegalArgumentException("Unknown task type");
        }
    }
}
