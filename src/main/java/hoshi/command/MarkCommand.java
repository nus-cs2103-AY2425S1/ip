package hoshi.command;

import hoshi.exception.HoshiException;
import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Storage;

/**
 * MarkCommand where the logic for marking a task is handled
 */
public class MarkCommand implements Command {

    /**
     * task index of the task to be marked
     */
    private final int taskIndex;
    /**
     * boolean indicating whether to mark or unmark a task
     */
    private final Boolean isMark;

    /**
     * MarkCommand constructor
     */
    public MarkCommand(String[] splitInput, Boolean isMark) {
        this.taskIndex = Integer.parseInt(splitInput[1]) - 1;
        this.isMark = isMark;
    }

    /**
     * Executes the logic for marking a task
     *
     * @param tasks  the TaskList that stores 3 types of tasks
     * @param ui     Ui that handles all user interaction
     * @param storage Storage that handles all input output of Hoshi
     * @return response corresponding to the function that was called depending on user input
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // if index is invalid
            if (taskIndex >= tasks.size()) {
                throw new HoshiException("Hoshi doesn't have such a task!");
            }

            // assert retrieved index is not out of bounds
            assert taskIndex < tasks.size() && taskIndex >= 0 : "Index is out of bounds for tasks";

            // mark or unmark the specified task
            Boolean isMark = this.isMark;
            tasks.get(taskIndex).setIsDone(isMark);
            CommandUtils.handleSave(tasks, storage, ui);

            // display corresponding UI to mark or unmark command
            if (isMark) {
                return ui.displayTaskMarked(tasks.get(taskIndex));
            } else {
                return ui.displayTaskUnmarked(tasks.get(taskIndex));
            }
        } catch (HoshiException e) {
            return ui.displayError(e.getMessage());
        }
    }
}
