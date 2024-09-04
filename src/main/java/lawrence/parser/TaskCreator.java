package lawrence.parser;

import lawrence.task.Task;

/**
 * Represents an object that can receive a string as input
 * and convert it into a relevant {@link Task} object.
 */
public interface TaskCreator {
    /**
     * Converts the given input into a {@link Task} object.
     *
     * @param input the string containing information about a task object
     * @return a {@link Task} object
     */
    Task createTask(String input);
}
