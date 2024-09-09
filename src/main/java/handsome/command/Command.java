package handsome.command;

import java.io.IOException;

import handsome.Storage;
import handsome.TaskList;
import handsome.Ui;
import handsome.exception.HandsomeException;
import handsome.exception.InvalidSyntaxException;
import handsome.exception.ListIndexOutOfBoundException;

/**
 * The Command class is an abstract base class for all command types in
 * the Handsome chatBot application. It defines the structure and common
 * functionality for commands, such as executing the command and determining if
 * the command ends the session.
 */
public abstract class Command {
    /**
     * Executes the command, performing the required actions on the task list, storage, and user interface.
     *
     * @param tasks   The TaskList object containing the tasks to be manipulated.
     * @param storage The Storage object used for saving and loading task data.
     * @param ui      The Ui object responsible for interacting with the user.
     * @throws HandsomeException If there is an error during the command execution.
     * @throws IOException       If an I/O error occurs while interacting with the storage.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws HandsomeException, IOException;

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return true if the command ends the chatBot session; false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Converts a string representation of a number to an integer, checking that the number
     * is within valid bounds of the task list size.
     *
     * @param string The string to be converted to an integer.
     * @param size   The size of the task list, used to validate the index.
     * @return The integer value if within bounds.
     * @throws ListIndexOutOfBoundException If the number is out of the valid range.
     * @throws InvalidSyntaxException       If the string is null, empty, or not a valid integer.
     */
    public int toNumber(String string, int size) throws ListIndexOutOfBoundException, InvalidSyntaxException {
        if (string == null || string.isEmpty()) {
            throw new InvalidSyntaxException();
        }
        try {
            int intValue = Integer.parseInt(string);
            if (intValue > size || intValue <= 0) {
                throw new ListIndexOutOfBoundException(size);
            }
            return intValue;
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException();
        }
    }
}
