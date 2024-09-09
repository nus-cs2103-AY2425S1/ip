package ned.commands;

import ned.TaskList;
import ned.Storage;
import ned.Ui;
import ned.exceptions.MissingSearchTermException;
import ned.exceptions.NedException;

/**
 * Represents the find command, which when executed, searches for all tasks with the term as a substring, and causes
 * it to be displayed to the user.
 */
public class FindCommand implements Command {
    private static final String FIND_MISSING_SEARCH_ERROR_MESSAGE = "Sorry m'lord, it seems that your find command is "
            + "missing a search term.";
    private final String REGEX = "^find.*";

    /**
     * Executes the find command, by checking first for whether any search term was specified, and if so it searches
     * for any tasks that contain the search term as a substring.
     *
     * @param taskList        An object which contains the ArrayList that stores the list of tasks. In addition, also
     *                        handles modifications and reading from the ArrayList
     * @param uiInstance      An object which handles output that is displayed to users and input from users
     * @param storageInstance An object which handles loading in and modifications to the cached list of tasks
     * @param userInput       A string which represents input from users into Ned
     * @throws NedException Thrown if the number specified in the delete command is not a valid number or if the
     *                      number is not a valid index
     */@Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput)
            throws NedException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < 1) {
            throw new MissingSearchTermException(FIND_MISSING_SEARCH_ERROR_MESSAGE);
        }
        String parsedInput = splitInput[1];
        TaskList listOfRelatedTasks = taskList.findRelatedTasks(parsedInput);
        listOfRelatedTasks.listTasks(uiInstance);
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
