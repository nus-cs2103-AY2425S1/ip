package bob.command;

import bob.UI;
import bob.tasks.TaskList;

/**
 * FindCommand is a child class of Command
 * It takes in a search string during initialization and then filters the TaskList for the search string when executed.
 * Sets the isRunning param of the parent class to be true signalling that the program is still running.
 */
public class FindCommand extends Command {

    private final String searchString;

    /**
     * Constructor for FindCommand
     * Sets parent parameter isRunning to true
     *
     * @param searchString Search string to filter for in the TaskList
     */
    public FindCommand(String searchString) {
        super(true);
        this.searchString = searchString;
    }

    /**
     * Filters the TaskList given based on the search string and then prints it
     *
     * @param taskList TaskList that is to be filtered
     */
    public void execute(TaskList taskList) {
        UI.printMessage(taskList.filter(this.searchString));
    }
}
