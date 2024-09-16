package storage.TaskStorage.TaskManager;

import exceptions.BrockException;
import task.Task;

abstract public class TaskManager {
    abstract public Task convertToTaskObject(String taskBody, char taskStatus) throws BrockException;
}
