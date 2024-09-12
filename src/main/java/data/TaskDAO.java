package data;

import java.util.List;
import java.util.Optional;
import features.task.Task;

public interface TaskDAO {

    /**
     * Retrieves a list of all tasks.
     *
     * @return A list of all tasks.
     */
    List<Task> getAllTasks();

    /**
     * Finds a task by its unique identifier.
     *
     * @param id The ID of the task to find.
     * @return An Optional containing the found task, or empty if not found.
     */
    Optional<Task> findTaskById(int id);

    /**
     * Adds a new task to the data store.
     *
     * @param task The task to add.
     */
    void addTask(Task task);

    /**
     * Updates an existing task in the data store.
     *
     * @param task The updated task.
     */
    void updateTask(Task task);

    /**
     * Deletes a task from the data store.
     *
     * @param id The ID of the task to delete.
     * @return The deleted task, or null if not found.
     */
    Task deleteTask(int id);
}

