package mahesh.command;

import mahesh.util.TaskList;

/**
 * Represents a command to find tasks in the TaskList that match a given search term.
 */
public class FindCommand extends Command {
    private TaskList list;
    private String searchTerm;

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
     */
    public void execute() {
        list.findTaskInList(searchTerm);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as FindCommand is not an exit command
     */
    public boolean isExit() {
        return false;
    }
}
