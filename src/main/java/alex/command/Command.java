package alex.command;

import java.io.IOException;


import alex.TaskList;
import alex.Ui;
import alex.Storage;
import alex.AlexException;

/**
 * Encapsulates a user command to be executed.
 */
public abstract class Command {

    /**
     * Executes user command.
     * Takes different forms based on the type of command.
     *
     * @param tasks Tasklist that holds the list of Tasks.
     * @param ui Ui object that displays messages to user based on action taken by chatbot.
     * @param storage Storage object that saves changes to file.
     * @throws AlexException If there are issues that occur when trying to execute user command.
     * @throws IOException If there are issues when trying to save changes to file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException;

    /**
     * Returns a boolean value that tells the chatbot whether to terminate its operations or not.
     */
    public abstract boolean isExit();
}
