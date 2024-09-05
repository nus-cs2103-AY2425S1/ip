package morgana.commands;

import morgana.storage.Storage;
import morgana.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
