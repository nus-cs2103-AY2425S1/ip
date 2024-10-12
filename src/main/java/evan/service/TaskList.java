package evan.service;

import java.util.ArrayList;

import evan.exception.NoSuchTaskException;
import evan.task.Task;


/**
 * Represents the list of tasks that the chatbot stores.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Instantiates an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Instantiates a TaskList object that is pre-populated with Tasks from the specified ArrayList.
     *
     * @param tasks ArrayList of Tasks that will pre-populate the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new Task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the Task with the given taskNumber as done.
     *
     * @param taskNumber Task number of the task that will be marked as done.
     * @throws NoSuchTaskException If there is no task with the specified taskNumber.
     */
    public void markTaskAsDone(int taskNumber) throws NoSuchTaskException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new NoSuchTaskException(taskNumber);
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the Task with the given taskNumber as undone.
     *
     * @param taskNumber Task number of the task that will be marked as undone.
     * @throws NoSuchTaskException If there is no task with the specified taskNumber.
     */
    public void markTaskAsUndone(int taskNumber) throws NoSuchTaskException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new NoSuchTaskException(taskNumber);
        }
        tasks.get(index).markAsUndone();
    }

    /**
     * Deletes the Task with the given taskNumber.
     *
     * @param taskNumber Task number of the task that will be deleted.
     * @throws NoSuchTaskException If there is no task with the specified taskNumber.
     */
    public void deleteTask(int taskNumber) throws NoSuchTaskException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new NoSuchTaskException(taskNumber);
        }
        tasks.remove(index);
    }

    /**
     * Retrieves all Tasks with descriptions that match the given description.
     *
     * @return TaskList containing Tasks with descriptions that match the given description.
     */
    public TaskList getTasksWithMatchingDescription(String description) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsMatchingDescription(description)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Returns a String representation of the TaskList object that will be saved to the storage .txt file.
     * Each Task in the TaskList has its own encodeAsString() method that will be called.
     *
     * @return String representation of the TaskList object that will be saved to the storage .txt file.
     */
    public String encodeAsString() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.encodeAsString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a string representation of the TaskList.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return result.toString().strip();
    }
}
