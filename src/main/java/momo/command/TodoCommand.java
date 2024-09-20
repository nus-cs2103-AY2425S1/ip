package momo.command;

import momo.Storage;
import momo.StorageException;
import momo.exception.InvalidCommandException;
import momo.task.Task;
import momo.task.TaskList;
import momo.task.Todo;

/**
 * Represents the command for adding Todo Tasks into the {@link Storage} and {@link TaskList},
 * validating user input which begins with "todo" and adding it to the {@link TaskList}.
 * If user input is not properly formatted it throws a {@link InvalidCommandException}.
 */
public class TodoCommand extends AddCommand {

    public static final int COMMAND_PREFIX_OFFSET = 4;
    /**
     * Executes the Todo command, creating a new Todo task based on the user input,
     * adding it to the task list, and updating the storage file. The method validates
     * the input to ensure that the task description is not empty, then constructs a
     * Todo task and updates both the in-memory task list and the persistent storage.
     *
     * @param input The user input that specifies the description of the Todo task. It is expected that the input
     *        starts with the command keyword (e.g., "todo") followed by the description.
     * @param storage The storage handler responsible for saving the new Todo task to persistent storage.
     * @param tasks  The task list to which the new Todo task will be added.
     * @return A string message indicating that the Todo task has been successfully added and
     *         providing the current count of tasks in the list.
     * @throws InvalidCommandException If the input does not contain a valid task description.
     * @throws StorageException        If there is an error saving the task to persistent storage.
     */
    public static String run(String input, Storage storage, TaskList tasks) throws InvalidCommandException,
            StorageException {
        String desc = input.substring(COMMAND_PREFIX_OFFSET).trim();

        if (desc.isEmpty()) {
            throw new InvalidCommandException("Where's the description of your task?!");
        }

        Task todo = new Todo(desc, false);

        tasks.addTask(todo);
        addToStorage(storage, todo);

        return "Noted. I've added this task:\n" + todo + returnCountString(tasks);
    }

}
