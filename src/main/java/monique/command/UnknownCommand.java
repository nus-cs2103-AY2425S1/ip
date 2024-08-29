package monique.command;

import monique.exception.MoniqueException;
import monique.exception.UnknownCommandException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

/**
 * Represents a command for handling unknown or invalid commands.
 * This command is used when the input command does not match any recognized commands.
 */
public class UnknownCommand extends Command {

    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true since bot should remain active after the Unknown Command
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Executes the UnknownCommand, which throws an <code>UnknownCommandException</code>.
     * This exception indicates that the command is unknown or invalid.
     *
     * @param tasks the <code>TaskList</code> (not used in this command)
     * @param ui the <code>Ui</code> instance (not used in this command)
     * @param storage the <code>Storage</code> instance (not used in this command)
     * @throws MoniqueException always throws <code>UnknownCommandException</code>
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MoniqueException {
        throw new UnknownCommandException();
    }

    /**
     * Determines if two <code>UnknownCommand</code> instances are equal.
     * All instances of <code>UnknownCommand</code> are considered equal.
     *
     * @param obj the object to be compared
     * @return true if the object is an instance of <code>UnknownCommand</code> or the same instance, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) {
            return true;
        }

        // Check if the object is an instance of UnknownCommand
        if (obj instanceof UnknownCommand) {
            return true; // All UnknownCommand instances are considered equal
        }

        // If obj is not an instance of UnknownCommand, return false
        return false;
    }
}
