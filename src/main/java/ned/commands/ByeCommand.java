package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.NedException;

/**
 * Represents the bye command, which when executed, causes a bye message to be displayed and the program to exit.
 */
public class ByeCommand implements Command {
    private final String REGEX = "bye\\s*";

    /**
     * Represents the command activated when the user types 'bye' with Ned.
     */
    public ByeCommand() {
    }

    /**
     * This method displays the bye message using the Ui instance associated with the Ned instance.
     *
     * @param taskList        The TaskList instance associated with the Ned instance, used to store the list of tasks
     *                        and to modify them
     * @param uiInstance      The Ui instance associated with the Ned instance, used to display user output, and take in
     *                        user input
     * @param storageInstance The storage instance associated with the Ned instance, used to load in saved tasks
     *                        from the cache file as well as to modify the cache file when the list of tasks are
     *                        changed
     * @param userInput       The string of user input, is parsed for relevant information needed to execute commands
     * @throws NedException A generic exception associated with the Ned class. It is caught and its message shown
     *                      to the user
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput)
            throws NedException {
        uiInstance.getByeMessage();
    }

    /**
     * A getter method which gets the b
     *
     * @return The value that indicates the Ned instance should exit after a user types in bye
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the regex expression used to identify the bye command
     * Used in Parser class to find the associated command
     *
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
