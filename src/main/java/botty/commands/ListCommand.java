package botty.commands;

import botty.exceptions.BottyException;
import botty.exceptions.TaskListEmptyException;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of the List command
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, returning the list of tasks as a message
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if given input is invalid
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws TaskListEmptyException {
        return "Here you go!\n" + taskManager.list();
    }
}
