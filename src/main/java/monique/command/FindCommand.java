package monique.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import monique.storage.Storage;
import monique.task.Task;
import monique.tasklist.TaskList;
import monique.ui.Ui;


/**
 * Represents a command to find tasks in the task list based on a search key.
 * <p>
 * This command searches for tasks that contain a specified search key in their description
 * and provides the search results to the user interface.
 * </p>
 */
public class FindCommand extends Command {

    private final String[] searchKeys;
    private String foundResults = "";

    /**
     * Constructs a {@code FindCommand} with the specified search key.
     * Uses a variable number of arguments
     * @param searchKeys the keys to search for in task descriptions
     */
    //Overloaded Constructor
    public FindCommand(String ...searchKeys) {
        super();
        this.searchKeys = searchKeys;
    }

    /**
     * Executes the find command, searching for tasks that contain the search key
     * in their description and updating the UI with the results.
     *
     * @param tasks the task list to search through
     * @param ui the user interface for displaying search results
     * @param storage the storage used to save the tasks (not used in this method)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Create a stream that filters tasks based on searchKeys
        List<Task> resultList = tasks.getTaskList().stream()
                .filter(task -> Arrays.stream(searchKeys)
                        .anyMatch(searchKey -> task.getDescription()
                        .contains(searchKey)))
                        .collect(Collectors.toList());

        this.foundResults = ui.showFindResults((ArrayList<Task>) resultList);
    }

    /**
     * Returns whether the command is active.
     *
     * @return true, as the find command is always active
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Retrieves the response message from the find command execution.
     *
     * @param ui the user interface instance (not used in this method)
     * @return a string containing the results of the find operation
     */
    @Override
    public String getResponse(Ui ui) {
        return this.foundResults;
    }
}
