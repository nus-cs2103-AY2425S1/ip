package morgana.commands;

import morgana.storage.Storage;
import morgana.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Bye! Hope to see you again soon!";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return MESSAGE_EXIT_ACKNOWLEDGEMENT;
    }
}
