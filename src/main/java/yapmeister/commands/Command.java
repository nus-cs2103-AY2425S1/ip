package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.TaskList;

/**
 * Represents a user command
 */
public interface Command {
    /**
     * Executes the command
     * @param tasks
     * @param storage
     * @param ui
     * @throws Exception
     */
    abstract void execute(TaskList tasks, Storage storage, UI ui) throws Exception;

    /**
     * Takes in an input, parses it and returns the Command
     * @param input
     * @return
     * @throws Exception
     */
    abstract Command parse(String input) throws Exception;
}
