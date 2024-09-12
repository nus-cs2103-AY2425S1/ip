package beeboo.command;

import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.BeeBooExceptions;
import beeboo.exception.InvalidCommandException;

/**
 * Represents an abstract command that can be executed by the chatbot.
 * Each specific command should extend this class and provide its own implementation of the execute method.
 */
public abstract class Command {
    protected String command;

    /**
     * Constructs a Command with a specific command string.
     *
     * @param command The command string to be executed.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Constructs a Command with no specific command string.
     */
    public Command() {
        command = null;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The task list that the command will operate on.
     * @param ui      The UI component to display messages.
     * @param storage The storage component to save or load data.
     * @throws BeeBooExceptions If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BeeBooExceptions, InvalidCommandException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return true if the command should terminate the chatbot, false otherwise.
     */
    public abstract boolean isExit();

}
