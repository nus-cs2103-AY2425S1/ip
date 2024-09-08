package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of the exit command
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command
     * @param taskManager
     * @param parsedInput
     * @return success message
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) {
        return "Goodbye! Thanks for your continued patronage.";
    }
    /**
     * Returns true since this is the exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
