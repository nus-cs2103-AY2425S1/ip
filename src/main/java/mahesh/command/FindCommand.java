package mahesh.command;

import mahesh.util.TaskList;

/**
 * Represents a command to find tasks in the TaskList that match a given search term.
 */
public class FindCommand extends Command {
    private final TaskList list;
    private final String searchTerm;

    /**
     * Constructs a FindCommand with the specified TaskList and search term.
     *
     * @param list       the TaskList in which to search for tasks
     * @param searchTerm the term to search for in the TaskList
     */
    public FindCommand(TaskList list, String searchTerm) {
        this.list = list;
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the FindCommand by searching for tasks in the TaskList that match the search term.
     *
     * @return a String response with the matching tasks or an error message if no matches are found
     */
    @Override
    public String execute() {
        return list.findTaskInList(searchTerm);
    }
}