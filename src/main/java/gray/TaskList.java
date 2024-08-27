package gray;

import gray.task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a task empty list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a list containing the elements of the specified collection,
     * in the order of the given list of tasks.
     *
     * @param taskList
     */
    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Returns the number of tasks.
     *
     * @return the number of tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Appends the specified task to the end of the task list.
     *
     * @param task
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index
     * @return the task specified
     */
    public Task get(int index) { return taskList.get(index); }

    /**
     * Removes the task at the specified position in this task list.
     * Shifts any subsequent task to the left (subtracts one from their indices).
     *
     * @param index
     * @return the task removed
     */
    public Task remove(int index) { return taskList.remove(index); }
}
