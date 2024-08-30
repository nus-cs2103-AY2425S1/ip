package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.exception.AiException;

/**
 * Class that executes user commands.
 */
public abstract class Command {
    /**
     * Executes the command given.
     *
     * @param tasks TaskList to be modified.
     * @param ui    Ui to print any UI elements.
     * @throws AiException if the command fails to execute.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws AiException;

    /**
     * Checks whether the command will result in exiting the programme.
     *
     * @return boolean True if command is bye, else false.
     */
    public abstract boolean isExit();
}
