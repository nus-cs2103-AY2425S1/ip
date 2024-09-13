package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;


/**
 *  The DeleteCommand class represents a command to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {
    /** Represents the index of the class within the TaskList **/
    private int index;

    /**
     * The constructor for an DeleteCommand that deletes the task at index.
     *
     * @param index of the task within the TaskList
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * In DeleteCommand, it gets the task at the given index from the TaskList
     * and deletes it.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @throws ChatBotException if index is out of bounds
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }

        Task removedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.save(tasks);
        return ui.showDeleteTask(removedTask, tasks.size());

    }

    /**
     * {@inheritDoc}
     *
     * In DeleteCommand, it will always return false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
