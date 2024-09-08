package qwerty;

import java.util.ArrayList;

import qwerty.task.Task;

/**
 * This class encapsulates a TaskList object.
 * A TaskList handles operations such as the addition, deletion and marking of tasks.
 */
public class TaskList {

    /** List containing the Task objects */
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        tasks = list;
    }

    /**
     * Returns the number of tasks currently in the TaskList.
     *
     * @return Current size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index in the TaskList.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index - 1);
    }

    /**
     * Returns a string enumerating the tasks in the TaskList.
     *
     * @return String representing the tasks in the TaskList.
     */
    public String listTasks() {
        int taskNumber = 1;
        StringBuilder out = new StringBuilder();
        for (Task task: tasks) {
            out.append("\n").append(taskNumber).append(".").append(task);
            taskNumber++;
        }
        return out.toString();
    }

    /**
     * Returns a string representing the list of tasks containing the specified substring.
     *
     * @param substring The substring to search for.
     * @return String describing the matching tasks.
     */
    public String findAndListTasks(String substring) {
        int taskNumber = 1;
        StringBuilder out = new StringBuilder();
        for (Task task: tasks) {
            if (task.descriptionContainsString(substring)) {
                out.append("\n").append(taskNumber).append(".").append(task);
                taskNumber++;
            }
        }
        return out.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked, starting from 1.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void markTaskAsDone(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked, starting from 1.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void markTaskAsNotDone(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).markAsNotDone();
    }

    /**
     * Returns a string generated from the tasks currently in the task list.
     * The string contains all the details needed to reconstruct each task,
     * making it suitable to be used in a save file. Uses streams.
     *
     * @return String containing task details.
     */
    public String generateSaveString() {
        return tasks.stream()
                .map(Task::getAllDetails)
                .map(x -> String.join("|", x) + "\n")
                .reduce("", (s1, s2) -> s1 + s2);
    }
}
