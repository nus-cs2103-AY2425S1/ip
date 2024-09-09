package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.InvalidIndexException;
import ned.exceptions.MissingIndexException;
import ned.exceptions.NedException;

/**
 * Represents the unmark command, which when executed, retrieves the selected task, by index from the list of tasks
 * and marks it as not done. The status change is shown to the user.
 */
public class UnmarkCommand implements Command {
    private static final String UNMARK_MISSING_INDEX_ERROR_MESSAGE = "Sorry m'lord, you must give me a list index with "
            + "the mark command. No more, no less";
    private static final String UNMARK_INDEX_NOT_NUMBER_ERROR_MESSAGE = "Sorry m'lord, your command must specify " +
            "a valid number";
    private static final String UNMARK_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "Sorry m'lord, seems the item number you " +
            "specified is not valid";
    private final String REGEX = "^unmark.*";

    public UnmarkCommand() {
    }

    /**
     * Unmarks a task in the list of tasks, making it not done.
     *
     * @param taskList        An object which contains the ArrayList that stores the list of tasks. In addition, also
     *                        handles modifications and reading from the ArrayList.
     * @param uiInstance      An object which handles output that is displayed to users and input from users.
     * @param storageInstance An object which handles loading in and modifications to the cached list of tasks.
     * @param userInput       A string which represents input from users into Ned.
     * @throws NedException Thrown if the index is not a number or a valid index.
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput)
            throws NedException {
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new MissingIndexException(UNMARK_MISSING_INDEX_ERROR_MESSAGE + uiInstance.getCommandMessage());
        }
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex) - 1;
            taskList.markTaskAsUndone(index, uiInstance);
            storageInstance.save(taskList);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(UNMARK_INDEX_NOT_NUMBER_ERROR_MESSAGE
                    + uiInstance.getCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(UNMARK_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE + uiInstance.getCommandMessage());
        }
    }

    /**
     * Returns the regex expression used to identify the mark command
     * Used in Parser class to find the associated command
     *
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
