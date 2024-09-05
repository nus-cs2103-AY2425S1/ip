package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.exceptions.InvalidTaskNumberException;

/**
 * Command to display all tasks
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Fetches tasks from session storage and prints to UI
     *
     * @param storage - permanent storage
     * @param tasks   - session storage
     * @throws InvalidTaskNumberException - Should not be triggered
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws InvalidTaskNumberException {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            s.append(String.format("%d. %s\n", i + 1, tasks.getTask(i)));
        }
        return s.toString();
    }
}
