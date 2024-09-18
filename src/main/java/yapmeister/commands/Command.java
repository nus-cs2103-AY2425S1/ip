package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.TaskList;

// Solution inspired by https://github.com/Dominic-Khoo/ip/tree/master

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
     * @return Command
     * @throws Exception
     */
    abstract Command parse(String input) throws Exception;
}
