package commands;

import applemazer.*;

public abstract class Command {
    /**
     * Method to execute a parsed user command.
     * @param tasks The task list to use if necessary.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     */
    public abstract void execute(TaskList tasks, Storage storage);

    /**
     * @return Returns true if the chatbot should continue running after executing the command.
     */
    public abstract boolean continueProcessing();

    public enum IntegerCommands {
        Mark,
        Unmark,
        Delete
    }
}
