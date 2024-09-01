package task;

import exceptions.AlreadyCompletedException;
import exceptions.TaskDoesNotExistException;

import java.util.ArrayList;

/**
 * A list of tasks stored by Him.
 *
 * @author IsaacPangTH
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for<code>TaskList</code>.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the task list as complete.
     *
     * @param index Index of the task in the list.
     * @throws AlreadyCompletedException If task has already been completed.
     * @throws TaskDoesNotExistException If task of that index does not exist in list.
     */
    public void complete(int index) throws AlreadyCompletedException, TaskDoesNotExistException {
        try {
            Task task = tasks.get(index);
            if (task == null) {
                throw new TaskDoesNotExistException(index);
            }
            task.complete();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException(index);
        }
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at index specified.
     */
    public String taskAt(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Returns<code>true</code>if task list is empty.
     *
     * @return <code>true/code>if list has no elements.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Removes the task at the specified index from the task list.
     *
     * @param index Index of the task.
     * @return Task deleted.
     * @throws TaskDoesNotExistException If task not in list.
     */
    public String delete(int index) throws TaskDoesNotExistException {
        try {
            Task task = tasks.get(index);
            if (task == null) {
                throw new TaskDoesNotExistException(index);
            }
            tasks.remove(index);
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException(index);
        }
    }

    /**
     * Returns data of the task list as a string.
     *
     * @return Data of task list in string form.
     */
    public String toData() {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.toData()).append("\n");
        }
        return data.toString();
    }

    /**
     * Returns list of tasks containing the keyword.
     *
     * @param keyword Keyword used for the search.
     * @return List of tasks containing the keyword in string form.
     */
    public String find(String keyword) {
        int count = 0;
        StringBuilder list = new StringBuilder();

        for (Task task : tasks) {
            if (task.descriptionContains(keyword)) {
                count++;
                list.append(String.format("%d.[%s][%s] %s\n", count, task.getTypeIcon(), task.getStatusIcon(), task));
            }
        }

        if (list.isEmpty()) {
            return "No results of containing that keyword found";
        }

        return list.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(
                    String.format("%d.[%s][%s] %s\n", i + 1, tasks.get(i).getTypeIcon(), tasks.get(i).getStatusIcon(),
                            tasks.get(i)));
        }
        return output.toString();
    }
}