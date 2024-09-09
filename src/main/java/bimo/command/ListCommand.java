package bimo.command;

import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command that list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists all task created.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list must not be null";
        String response = ui.printListOfTasks(tasks);
        return response;
    }
}
