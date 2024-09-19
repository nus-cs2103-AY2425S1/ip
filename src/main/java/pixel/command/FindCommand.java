package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.TaskList;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, finding tasks that match the keyword and displaying
     * them in the UI.
     *
     * @param tasks   the list of tasks to search in
     * @param ui      the user interface to display the matching tasks
     * @param storage the storage to save the tasks
     * @throws PixelException if there is an error executing the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PixelException {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Executes the FindCommand and returns the response message.
     *
     * @param tasks   the list of tasks to search in
     * @param ui      the user interface to display the matching tasks
     * @param storage the storage to save the tasks
     * @return the response message
     * @throws PixelException if there is an error executing the command
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws PixelException {
        TaskList matchingTasks = tasks.findTasks(keyword);
        return ui.getMatchingTasksResponse(matchingTasks);
    }

    /**
     * Checks if the FindCommand is an exit command.
     *
     * @return false, as the FindCommand is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
