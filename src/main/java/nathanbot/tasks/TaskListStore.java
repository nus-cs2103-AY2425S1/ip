package nathanbot.tasks;

import nathanbot.storage.Storage;

/**
 * Manages a list of tasks with storage capabilities.
 */
public class TaskListStore extends TaskList {
    private final Storage storage;

    /**
     * Constructs a TaskListStore with the specified storage.
     *
     * @param storage The storage to be used for loading and saving tasks.
     */
    public TaskListStore(Storage storage) {
        super();
        this.storage = storage;
        this.taskList.addAll(storage.loadTasksFromFile());
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task The task to be added.
     */
    @Override
    public void addTask(Task task) {
        super.addTask(task);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Marks the task at the specified index as done and saves the updated list to storage.
     *
     * @param index The index of the task to be marked as done.
     */
    @Override
    public void markAsDone(int index) {
        super.markAsDone(index);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Marks the task at the specified index as undone and saves the updated list to storage.
     *
     * @param index The index of the task to be marked as undone.
     */
    @Override
    public void markAsUndone(int index) {
        super.markAsUndone(index);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Deletes the task at the specified index and saves the updated list to storage.
     *
     * @param index The index of the task to be deleted.
     */
    @Override
    public void deleteTask(int index) {
        super.deleteTask(index);
        storage.saveTasksToFile(taskList);
    }
}