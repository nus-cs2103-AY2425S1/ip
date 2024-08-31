package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of the exit command
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, does not actually do anything
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if given input is invalid
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) {
        return "";
    }
    /**
     * Returns true since this is the exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
