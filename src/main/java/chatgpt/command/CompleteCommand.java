package chatgpt.command;

import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

/**
 *  The CompleteCommand class represents a command to mark a Task from the TaskList
 *  as complete/incomplete.
 */
public class CompleteCommand extends Command {
    /** Represents whether the task has been completed **/
    private boolean isCompleted;
    /** Represents the index of the class within the TaskList **/
    private int index;

    /**
     * The constructor for an CompleteCommand that marks the given task as
     * complete or incomplete.
     *
     * @param index of the task within the TaskList
     * @param isCompleted represents the status of the task
     */
    public CompleteCommand(int index, boolean isCompleted) {
        this.index = index;
        this.isCompleted = isCompleted;
    }

    /**
     * {@inheritDoc}
     *
     * In CompleteCommand, it gets the task at the given index from the TaskList
     * and marks it as complete/incomplete.
     *
     * @param tasks that tracks the application's tasks
     * @param ui that handles the printing and reading on inputs and outputs
     * @param storage that handles saving and reading text file with saved data
     * @throws ChatBotException if index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChatBotException {
        if (index > tasks.size()) {
            throw new ChatBotException("\tNo task exists for that index");
        }
        Task task = tasks.get(index - 1);
        task.setCompleted(isCompleted);
        if (isCompleted) {
            ui.showCompleteTask(task);
        } else {
            ui.showUncompleteTask(task);
        }
        storage.save(tasks);
    }

    /**
     * {@inheritDoc}
     *
     * In CompleteCommand, it will always return false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
