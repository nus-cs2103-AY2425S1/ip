package Niko.Command;

import Niko.Common.DateTimeParser;
import Niko.Common.NikoException;
import Niko.Main.Storage;
import Niko.Main.Ui;
import Niko.Task.TaskList;

/**
 * Represents a command to search for tasks containing a specific query in the task list.
 */
public class SearchCommand extends Command {
    private String query;

    /**
     * Constructs a SearchCommand with the specified query string.
     *
     * @param query The string to search for in the task list.
     */
    public SearchCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the search command, finding tasks that match the query
     * and displaying them to the user.
     *
     * @param tasks   The task list to search through.
     * @param ui      The UI to display the search results.
     * @param storage The storage (not used in this command).
     * @throws NikoException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikoException {
        DateTimeParser parser = new DateTimeParser();
        parser.searchTasks(query, tasks);
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return {@code false}, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
