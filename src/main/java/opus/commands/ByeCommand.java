package opus.commands;

import opus.exceptions.OpusException;
import opus.Storage;
import opus.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand implements Command {
    /**
     * Executes the command to exit the program.
     *
     * @param taskList The task list.
     * @param storage The storage.
     * @return The response to the user.
     * @throws OpusException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws OpusException {
        storage.save(taskList.getTasks());
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
