package botty.commands;

import botty.exceptions.BottyException;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of the find command
 */
public class FindCommand extends Command {
    /**
     * Executes the find command, returning the tasks that match the given keyword
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if no tasks were found matching the keyword
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        String keyword = parsedInput.getArgument("main");

        String matchedTasks = taskManager.findTasks(keyword);

        return "Here are the tasks that matched your search!\n" + matchedTasks;
    }
}
