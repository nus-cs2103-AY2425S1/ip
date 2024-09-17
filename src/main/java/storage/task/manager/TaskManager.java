package storage.task.manager;

import exceptions.BrockException;
import task.Task;

public abstract class TaskManager {
    public abstract Task convertToTaskObject(String taskBody, char taskStatus) throws BrockException;
}
