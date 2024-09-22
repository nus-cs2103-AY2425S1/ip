package storage.task.managers;

import exceptions.BrockException;
import task.Task;

/**
 * Abstract base class to provide a template for a task manager.
 */
public abstract class TaskManager {
    /**
     * Converts the task string into a task object.
     *
     * @param taskBody extracted from task string.
     * @param taskStatus extracted from task string.
     * @return Task object.
     * @throws BrockException If conversion fails.
     */
    public abstract Task convertToTaskObject(String taskBody, char taskStatus) throws BrockException;
}
