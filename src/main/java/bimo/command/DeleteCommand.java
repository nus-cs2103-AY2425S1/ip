package bimo.command;

import bimo.tasks.Task;
import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command that delete tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates command object that deletes task.
     *
     * @param index Index of task in list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes specified at index in task list.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!ui.findTaskInList(this.index, tasks)) {
            return ui.showTaskNotFoundError();
        }
        Task taskRemoved = tasks.removeTask(index);
        storage.overwriteFile(tasks);
        int length = tasks.getLength();
        String response = ui.sendDeleteTaskMessage(length, taskRemoved);
        return response;
    }
}
