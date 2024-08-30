package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Task;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

/**
 * Marks a task in the task list when this class is instantiated.
 */
public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param ui The current ui object the chatbot uses to display messages
     * @param storage The storage object the chatbot uses to store and load tasks
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new AtlasException("Task number does not exist.");
        }

        Task task = tasks.mark(this.index);
        storage.save();
        ui.print(String.format("Nice! I've marked this task as done:\n \t%s", task));
    }
}
