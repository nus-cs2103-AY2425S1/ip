package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of a command
 */
public abstract class Command {
    /**
     * Executes the command
     * @param taskManager
     * @param parsedInput
     * @return the success message
     * @throws BottyException
     */
    public abstract String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException;

    /**
     * Returns if the command will cause the application to exit
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns if the command will edit the task list
     */
    public boolean changesTaskList() {
        return false;
    }
}
