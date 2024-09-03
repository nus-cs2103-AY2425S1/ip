package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;

/**
 * Abstract class that represents a user command.
 */
public abstract class Command {
    /**
     * Method to execute a parsed user command.
     * <p>
     * Subclasses should provide their own implementation.
     *
     * @param tasks   The task list to use if necessary.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui The Ui object used to generate the string to print.
     * @return The string to print.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui);

    /**
     * Returns {@code true} if the chatbot should continue running after executing the command.
     * @return Either {@code true} or {@code false} depending on subclass implementation.
     */
    public abstract boolean continueProcessing();

    /**
     * Represents commands that handle integer input.
     */
    public enum IntegerCommands {
        Mark,
        Unmark,
        Delete
    }
}
