package Jay;

import Jay.command.InvalidCommandException;
import Jay.storage.DataIOException;
import Jay.storage.InvalidDataFormatException;
import Jay.storage.Storage;
import Jay.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private final Storage storage;

    public TaskList(String fileName) {
        this.tasks = new ArrayList<>();
        this.storage = new Storage("data", fileName);
        this.taskCount = 0;

        try {
            this.loadTasks();
        } catch (DataIOException | InvalidDataFormatException e) {
            this.tasks.clear();
            this.taskCount = 0;
        }
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
        this.storage = new Storage("data", "wildcard");
    }

    /**
     * Marks a Jay.task as done.
     *
     * @param taskNumber The index of the Jay.task need to be marked.
     * @return The marked Jay.task.
     * @throws InvalidCommandException If the Jay.task number is invalid.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public Task markAsDone(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);
        this.tasks.get(taskNumber - 1).markAsDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Mark a Jay.task as not done.
     *
     * @param taskNumber The index of the Jay.task.
     * @return The marked Jay.task.
     * @throws InvalidCommandException If the Jay.task number is invalid.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public Task markAsNotDone(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);
        this.tasks.get(taskNumber - 1).markAsNotDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Checks if the Jay.task number is valid.
     *
     * @param taskNumber The index of the Jay.task need to be checked.
     * @throws InvalidCommandException If the Jay.task number is invalid.
     */
    private void isValidTaskNumberCheck(int taskNumber) throws InvalidCommandException {
        if (!this.isValidTaskNumber(taskNumber)) {
            throw new InvalidCommandException("OOPS!!! The Jay.task number is invalid.");
        }
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= this.taskCount;
    }

    /**
     * Checks if the Jay.task list is empty.
     *
     * @return True if the Jay.task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskCount == 0;
    }

    /**
     * Adds a Jay.task to the Jay.task list.
     *
     * @param task The Jay.task to be added.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public void addTask(Task task) throws DataIOException {
        this.tasks.add(task);
        this.taskCount++;
        this.saveTasks();
    }

    /**
     * Removes a Jay.task from the Jay.task list.
     *
     * @param taskNumber The index of the Jay.task need to be removed.
     * @return The removed Jay.task.
     * @throws DataIOException If there is an error saving the tasks.
     * @throws InvalidCommandException If the Jay.task number is invalid.
     */
    public Task removeTask(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);

        Task task = this.tasks.get(taskNumber - 1);
        this.taskCount--;

        for (int i = taskNumber - 1; i < this.taskCount; i++) {
            this.tasks.set(i, this.tasks.get(i + 1));
        }

        this.tasks.remove(this.taskCount);
        this.saveTasks();

        return task;
    }

    @Override
    public String toString() {
        StringBuilder tasksStr = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasksStr.append((i + 1)).append(". ").append(this.tasks.get(i));
            } else {
                tasksStr.append((i + 1)).append(". ").append(this.tasks.get(i)).append("\n");
            }
        }

        return tasksStr.toString();
    }

    /**
     * Returns the number of the tasks in the list in a nice format.
     *
     * @return The number of the tasks in the list in a nice format.
     */
    public String getTaskCount() {
        return "Now you have " + this.taskCount + " tasks in the list.";
    }

    /**
     * Returns the list of tasks in a simple format.
     *
     * @return The list of tasks in a simple format.
     */
    private String getSimpleTaskList() {
        StringBuilder tasksStr = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasksStr.append(this.tasks.get(i).simpleFormat());
            } else {
                tasksStr.append(this.tasks.get(i).simpleFormat()).append("\n");
            }
        }

        return tasksStr.toString();
    }

    private void saveTasks() throws DataIOException {
        this.storage.saveTasks(this.getSimpleTaskList());
    }

    private void loadTasks() throws DataIOException, InvalidDataFormatException {
        this.tasks = this.storage.loadTasks();
        this.taskCount = tasks.size();
    }

    /**
     * Finds tasks in the Jay.task list that contain the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> foundTasks = this.tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        return new TaskList(new ArrayList<>(foundTasks));
    }
}
