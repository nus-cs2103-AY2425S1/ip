package ollie.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import ollie.Storage;
import ollie.exception.OllieException;
import ollie.ui.Ui;

/**
 * The TaskList manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private Storage storage;

    /**
     * Constructs a ollie.task.TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Constructs a ollie.task.TaskList and loads tasks from storage.
     * If the storage file does not exist, an empty list is created.
     *
     * @param tasks The list of tasks.
     * @param storage The storage object for saving and loading tasks.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.taskCount = tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * List all tasks in the task list.
     *
     * @return The list of tasks.
     */
    public String listTasks() {
        return Ui.listTasks(tasks);
    }

    /**
     * Marks a specific task as done based on the task number.
     *
     * @param taskNumber The task number to mark as done.
     * @return
     */
    public String markTaskAsDone(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone(true);
            storage.saveTasks(tasks);
            return Ui.markTaskAsDone(tasks.get(taskNumber));
        } else {
            return Ui.returnErrorAsString(
                    new OllieException("Please enter a valid task number within the list ☺"));
        }
    }

    /**
     * Unmarks a specific task as not done based on the task number.
     *
     * @param taskNumber The task number to unmark as done.
     * @throws OllieException If the task number is invalid.
     */
    public String unmarkTaskAsDone(int taskNumber) throws OllieException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markAsDone(false);
            storage.saveTasks(tasks);
            return Ui.unmarkTaskAsDone(tasks.get(taskNumber));
        } else {
            return Ui.returnErrorAsString(
                    new OllieException("Please enter a valid task number within the list ☺"));
        }
    }

    /**
     * Adds a new task to the list and prints the formatted output.
     *
     * @param task The task to add.
     */
    public String addTask(Task task) {
        tasks.add(task);
        taskCount = tasks.size();
        if (storage != null) {
            storage.saveTasks(tasks);
        }
        return Ui.addTask(task, taskCount);
    }

    /**
     * Adds a new task to the list without printing the formatted output.
     *
     * @param task The task to add.
     */
    public void addTaskWihoutMessage(Task task) {
        tasks.add(task);
        taskCount = tasks.size();
        if (storage != null) {
            storage.saveTasks(tasks);
        }
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public String findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        return Ui.showFindResults(matchingTasks);
    }

    /**
     * Deletes a specific task based on the task number.
     *
     * @param taskNumber The task number to delete.
     * @throws OllieException If the task number is invalid.
     */
    public String deleteTask(int taskNumber) throws OllieException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            taskCount = tasks.size();
            storage.saveTasks(tasks);
            return Ui.deleteTask(removedTask, taskCount);
        } else {
            return Ui.returnErrorAsString(
                    new OllieException("Please enter a valid task number within the list ☺"));
        }
    }
}
