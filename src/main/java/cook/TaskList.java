package cook;

import java.io.Serializable;
import java.util.ArrayList;

import tasks.Task;

/**
 * TaskList class to store tasks.
 */
// Solution below adapted from https://stackoverflow.com
// /questions/13895867/why-does-writeobject-throw-java-io-notserializableexception-and-how-do-i-fix-it
public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    /**
     * Constructs TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks task.
     *
     * @param taskNumber Number of the task in tasks.
     * @return Success of marking task.
     * @throws IndexOutOfBoundsException If task number does not indicate a task in tasks.
     */
    public boolean markTask(int taskNumber) throws IndexOutOfBoundsException {
        int indexNo = taskNumber - 1;
        return this.tasks.get(indexNo).mark();
    }

    /**
     * Unmarks task.
     *
     * @param taskNumber Number of the task in tasks.
     * @return Success of unmarking task.
     * @throws IndexOutOfBoundsException If task number does not indicate a task in tasks.
     */
    public boolean unmarkTask(int taskNumber) throws IndexOutOfBoundsException {
        int indexNo = taskNumber - 1;
        return this.tasks.get(indexNo).unmark();
    }

    /**
     * Deletes task.
     *
     * @param taskNumber Number of the task in tasks.
     */
    public void deleteTask(int taskNumber) {
        int indexNo = taskNumber - 1;
        this.tasks.remove(indexNo);
    }

    /**
     * Finds task(s).
     *
     * @param keyword String to find in tasks' toString().
     * @return List of Task objects.
     */
    public TaskList findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        keyword = keyword.toLowerCase();
        for (Task task : this.tasks) {
            if (task.toString().toLowerCase().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Checks if there is a duplicate task in tasks.
     *
     * @param task Task object to find duplicate of.
     * @return Success of finding duplicate.
     */
    public boolean detectDuplicate(Task task) {
        return this.tasks.stream().anyMatch(task::equals);
    }

    /**
     * Checks if tasks is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder taskStringBuilder = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            int taskNumber = i + 1;
            Task task = this.tasks.get(i);
            taskStringBuilder.append(taskNumber).append(".").append(task.toString()).append("\n");
        }
        return taskStringBuilder.toString().strip();
    }
}
