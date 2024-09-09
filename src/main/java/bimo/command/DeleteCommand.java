package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;
import bimo.tasks.Task;


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
        assert tasks != null : "Task list must not be null";
        if (index >= tasks.getLength() || index < 0) {
            ui.showTaskNotFoundError();
            return ui.showTaskNotFoundError();
        }
        assert index >= 0 && index < tasks.getLength() : "Index must not be out of bounds";
        Task task = tasks.removeTask(index);
        assert task != null : "Task must not be null";
        storage.overwriteFile(tasks);
        String word = tasks.getLength() == 1 ? "task" : "tasks";
        String response = "Noted. I've removed this task:\n    "
                + task.toString() + String.format("\nNow you have %d %s in the tasks.",
                tasks.getLength(), word);
        return response;
    }
}
