package kat.chatbot;

import java.util.List;

import kat.tasks.Task;

/**
 * Represents storage system for managing tasks.
 */
public interface TaskStorage {

    /**
     * Adds a new task to the storage.
     *
     * @param task The task to be added.
     */
    void addTask(Task task);

    /**
     * Retrieves a task from the storage by its index.
     *
     * @param taskIdx The index of the task to retrieve.
     * @return The task at the specified index.
     */
    Task getTask(int taskIdx);

    /**
     * Returns the number of tasks in the storage.
     *
     * @return The total number of tasks.
     */
    int getSize();

    /**
     * Marks a task as done.
     *
     * @param taskIdx The index of the task to mark as done.
     */
    void setTaskAsDone(int taskIdx);

    /**
     * Marks a task as not done.
     *
     * @param taskIdx The index of the task to mark as not done.
     */
    void setTaskAsNotDone(int taskIdx);

    /**
     * Removes a task from the storage.
     *
     * @param taskIdx The index of the task to delete.
     */
    void deleteTask(int taskIdx);

    /**
     * Finds tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the keyword.
     */
    List<Task> findTasks(String keyword);

}
