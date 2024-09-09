package bimo.command;

import bimo.tasks.Task;
import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command to unmark tasks.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;
    /**
     * Instantiates object to set task as uncompleted.
     *
     * @param indexToUnmark Index of task inside list.
     */
    public UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * Sets status of task as uncompleted.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!ui.findTaskInList(this.indexToUnmark, tasks)) {
            return ui.showTaskNotFoundError();
        }
        tasks.getTask(indexToUnmark).markUncompleted();
        storage.overwriteFile(tasks);
        Task unmarkedTask = tasks.getTask(this.indexToUnmark);
        String response = ui.printTaskUnmarked(unmarkedTask);
        return response;
    }
}
