package command;

import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command.
     * Marks the task as done if it is not already marked, and saves the updated task list to storage.
     *
     * @param tasks   The TaskList object containing the current list of tasks.
     * @param ui      The Ui object for interacting with the user.
     * @param storage The Storage object for saving tasks to the storage file.
     * @throws BuddyException If the task index is invalid or there is an error saving the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";
        assert taskIndex < tasks.getTasks().size() && taskIndex >=0 : "task index should be [0, size)";

        if (taskIndex >= tasks.getTasks().size()) {
            return ui.displayUnavailableItem();
        } else if (tasks.isTaskDone(taskIndex)) {
            return ui.displayAlreadyMarked();
        } else {
            tasks.markTask(taskIndex);
            storage.save(tasks.getTasks());
            return ui.displayMarkedTask(taskIndex, tasks);
        }
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as the mark command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}