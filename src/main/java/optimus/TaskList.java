package optimus;

import java.util.ArrayList;
import java.util.stream.Stream;

import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

/**
 * Handles storage for current session
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedFromStorage) {
        this.list = loadedFromStorage;
    }

    /**
     * Returns a Task at a specific index
     *
     * @param taskNum - Index at which the task is at
     * @return -  Task if it exists
     * @throws InvalidTaskNumberException - Thrown when task does not exist or task number is invalid
     */
    public Task getTask(int taskNum) throws InvalidTaskNumberException {
        if (isInvalidTaskNum(taskNum)) {
            throw new InvalidTaskNumberException();
        } else {
            return list.get(taskNum);
        }
    }

    /**
     * Returns total number of tasks
     *
     * @return total number of tasks
     */
    public int getNumOfTasks() {
        return list.size();
    }

    /**
     * Removes a task at a specified index
     *
     * @param taskNum - Task index
     * @return - Task which was removed
     * @throws InvalidTaskNumberException - Thrown when task does not exist or task number is invalid
     */
    public Task removeTask(int taskNum) throws InvalidTaskNumberException {
        if (isInvalidTaskNum(taskNum)) {
            throw new InvalidTaskNumberException();
        } else {
            return list.remove(taskNum);
        }
    }

    /**
     * Returns a stream of Tasks whose descriptions contain the keyword
     *
     * @param keyword search input
     * @return stream of tasks matching the search input
     */
    public Stream<Task> filterByKeyword(String keyword) {
        return list.stream()
                .filter(task -> task.getTaskDesc().contains(keyword));
    }

    /**
     * Adds a task as the end of the list of tasks
     *
     * @param task - Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    private boolean isInvalidTaskNum(int taskNum) {
        return taskNum < 0 || taskNum >= list.size();
    }

    /**
     * Returns the list of tasks. Unsafe operation, use sparingly
     *
     * @return list of tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }
}
