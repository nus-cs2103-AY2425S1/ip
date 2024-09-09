package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates a command to set task as completed.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Instantiates object to set task as completed.
     *
     * @param index Index of task inside list.
     */
    public MarkCommand(int index) {
        this.index = index;
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
        if (index >= tasks.getLength() || index < 0) {
            return ui.showTaskNotFoundError();
        }
        assert index >= 0 && index < tasks.getLength() : "Index must not be out of bounds";
        tasks.getTask(index).markCompleted();
        storage.overwriteFile(tasks);
        String response = "Good job! I've marked this task as done:\n"
                + "    " + tasks.getTask(index).toString();
        return response;
    }
}
