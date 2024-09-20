package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

/**
 *  The ShowCommand class represents a command to view the details of a Task
 *  from the local TaskList.
 */
public class ShowCommand extends Command {
    /** Represents the index of the task within the TaskList **/
    private int index;

    /**
     * The constructor for a ShowCommand that will show the details of the task at
     * the index
     *
     * @param index of the task within the TaskList
     */
    public ShowCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * In ShowCommand, it gets the task at the given index from the local tasklist
     * and returns the details of that task including any notes.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @return String representing the details of the task including the notes
     * @throws ChatBotException if index is out of bounds
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        Task t = getTask(tasks);
        return ui.showTask(t);
    }

    private Task getTask(TaskList tasks) throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }
        return tasks.get(index - 1);
    }
}
