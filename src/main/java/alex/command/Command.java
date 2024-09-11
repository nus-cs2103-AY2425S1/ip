package alex.command;

import java.io.IOException;

import alex.AlexException;
import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

/**
 * Encapsulates a user command to be executed.
 */
public abstract class Command {
    /**
     * Executes the user command.
     * Takes different forms based on the type of command.
     *
     * @param tasks TaskList that holds the list of Tasks.
     * @param ui Ui object that displays messages to the user based on the action taken by the chatbot.
     * @param storage Storage object that saves changes to the file.
     * @return A string representing the result of the command execution.
     * @throws AlexException If there are issues that occur when trying to execute the user command.
     * @throws IOException If there are issues when trying to save changes to the file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AlexException, IOException;

    /**
     * Returns the type of the command.
     *
     * @return A string representing the command type.
     */
    public abstract String getCommandType();
}


