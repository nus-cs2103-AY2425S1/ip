package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;

/**
 * Represents a command to render assistance to the user, should they be confused.
 */
public class HelpCommand extends Command {
    private String helpText = "List of available commands:\n" +
            "t - add todo task\n" +
            "d - add delete task\n" +
            "e - add event task\n" +
            "mark - mark task as completed\n" +
            "unmark - mark task as NOT completed\n" +
            "rm - delete task\n" +
            "ls - list tasks\n" +
            "help - display available commands\n";

    /**
     * Executes the help command, which displays a list of available commands.
     *
     * @param taskList the TaskList to operate on
     * @param storage the storage system to interact with
     * @return the help text specifying the supported commands
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return helpText;
    }
}
