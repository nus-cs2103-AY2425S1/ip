package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Task;
import atlas.tasks.TaskList;

/**
 * Deletes a task from the task list when this class is instantiated.
 */
public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @throws AtlasException The exception to be thrown in the event of any error.
     *
     * @return String The message returned to be displayed on the chatbot GUI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AtlasException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task task = tasks.delete(this.index);
        storage.save();
        return String.format("Noted. I've removed this task:\n\t%s\n Now you have %s tasks in the list.",
                task, tasks.size());
    }
}
