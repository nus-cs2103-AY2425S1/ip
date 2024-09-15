package matcha.command;

import matcha.Matcha;
import matcha.storage.Storage;
import matcha.tasklist.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the program.
     * Says goodbye to user.
     *
     * @param tasks The task list containing all tasks.
     * @param storage Storage object to save tasks to file.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Matcha.setExit(true);
        super.setResponse("Goodbye! Hope to see you again soon!");
        return super.getResponse();
    }
}
