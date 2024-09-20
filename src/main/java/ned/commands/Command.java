package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.NedException;

/**
 * Represents a command, which when executed, causes the command logic to be carried out.
 */
public interface Command {
    /**
     * A generic method with each command class implementing the logic needed to carry out the command.
     *
     * @param taskList        An object which contains the ArrayList that stores the list of tasks. In addition, also
     *                        handles modifications and reading from the ArrayList
     * @param uiInstance      An object which handles output that is displayed to users and input from users
     * @param storageInstance An object which handles loading in and modifications to the cached list of tasks
     * @param userInput       A string which represents input from users into Ned
     * @throws NedException A type of exception, which wraps all other exceptions. Error messages are displayed to
     *                      users via Ui class
     */
    void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException;

    default boolean isExit() {
        return false;
    }

    /**
     * Returns the regex expression used to identify the command in each command class respectively
     * Used in Parser class to find the associated command
     *
     * @return The regex pattern associated with this command
     */
    String getRegex();
}
