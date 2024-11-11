package bimo.command;

import bimo.tasks.Task;
import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command to set task as completed.
 */
public class MarkCommand extends Command {

    private int indexToMark;

    /**
     * Instantiates object to set task as completed.
     *
     * @param indexToMark Index of task inside list.
     */
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Sets status of task as completed.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.isTaskInList(this.indexToMark, tasks)) {
            return ui.showTaskNotFoundError();
        }
        assert indexToMark >= 0 && indexToMark < tasks.getLength()
                : "Index must not be out of bounds";
        tasks.getTask(this.indexToMark).markCompleted();
        storage.overwriteFile(tasks);
        Task markedTask = tasks.getTask(this.indexToMark);
        String response = ui.printTaskMarked(markedTask);
        return response;
    }
}
