package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Task;
import atlas.tasks.TaskList;

/**
 * Unmarks a task in the task list when this class is instantiated.
 */
public class UnmarkCommand extends Command {
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks a task.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AtlasException {
        if (index < 0 || index >= tasks.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task task = tasks.unmark(this.index);
        storage.save();
        return String.format("OK, I've marked this task as not done yet:\n \t%s", task);
    }
}
