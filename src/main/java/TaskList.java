import command.InvalidCommandException;
import storage.DataIOException;
import storage.InvalidDataFormatException;
import storage.Storage;
import task.Task;

import java.util.ArrayList;

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

    /**
     * Marks a task as done.
     * @param taskNumber The index of the task need to be marked.
     * @return The marked task.
     * @throws InvalidCommandException If the task number is invalid.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public Task markAsDone(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);
        this.tasks.get(taskNumber - 1).markAsDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Mark a task as not done.
     * @param taskNumber The index of the task.
     * @return The marked task.
     * @throws InvalidCommandException If the task number is invalid.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public Task markAsNotDone(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);
        this.tasks.get(taskNumber - 1).markAsNotDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Checks if the task number is valid.
     * @param taskNumber The index of the task need to be checked.
     * @throws InvalidCommandException If the task number is invalid.
     */
    private void isValidTaskNumberCheck(int taskNumber) throws InvalidCommandException {
        if (!this.isValidTaskNumber(taskNumber)) {
            throw new InvalidCommandException("OOPS!!! The task number is invalid.");
        }
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= this.taskCount;
    }

    /**
     * Checks if the task list is empty.
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskCount == 0;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     * @throws DataIOException If there is an error saving the tasks.
     */
    public void addTask(Task task) throws DataIOException {
        this.tasks.add(task);
        this.taskCount++;
        this.saveTasks();
    }

    /**
     * Removes a task from the task list.
     * @param taskNumber The index of the task need to be removed.
     * @return The removed task.
     * @throws DataIOException If there is an error saving the tasks.
     * @throws InvalidCommandException If the task number is invalid.
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
     * @return The number of the tasks in the list in a nice format.
     */
    public String getTaskCount() {
        return "Now you have " + this.taskCount + " tasks in the list.";
    }

    /**
     * Returns the list of tasks in a simple format.
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
}
