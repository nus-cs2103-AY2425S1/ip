package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.Task;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents a command to mark or unmark a task as done or not done.
 * The command is executed on a specific task in the task list.
 */
public class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean isMark;
    private String response;

    /**
     * Constructs a MarkCommand with the specified task number and marking status.
     *
     * @param taskNumber The index of the task to be marked or unmarked.
     * @param isMark     {@code true} to mark the task as done, {@code false} to unmark it.
     */
    public MarkCommand(int taskNumber, boolean isMark) {
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    /**
     * Executes the mark or unmark command on the specified task.
     * If the task is successfully marked or unmarked, the task list is updated and changes are saved to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage to update the task list.
     * @throws LexiException If the task does not exist or if the task is already in the desired state.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        // Assertions for method preconditions
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "Ui object cannot be null";
        assert storage != null : "Storage object cannot be null";
        assert taskNumber >= 0 : "Task number must be non-negative";

        if (taskNumber > tasks.getSize() - 1) {
            throw new LexiException("Sorry! That task does not exist.\nPlease key in the correct task number");
        }

        Task taskToBeMarked = tasks.getTask(taskNumber);

        // Assertions to ensure the task is not null
        assert taskToBeMarked != null : "Task should not be null";

        if (isMark) {
            markTask(taskToBeMarked, tasks, ui);
        } else {
            unmarkTask(taskToBeMarked, tasks, ui);
        }

        // Assertion to ensure response is set
        assert response != null : "Response should be set after marking/unmarking the task";

        storage.updateStorage(tasks.getTasks());
    }

    /**
     * Unmarks the specified task, indicating it is not done.
     * The task list and UI are updated accordingly.
     *
     * @param taskToBeMarked The task to be unmarked.
     * @param tasks          The list of tasks.
     * @param ui             The UI object to interact with the user.
     * @throws LexiException If the task is already unmarked.
     */
    private void unmarkTask(Task taskToBeMarked, TaskList tasks, Ui ui) throws LexiException {
        // Assertion to check preconditions
        assert taskToBeMarked != null : "Task to be unmarked cannot be null";

        if (!taskToBeMarked.getIsDone()) {
            throw new LexiException("This task has already been unmarked!\n");
        }
        taskToBeMarked.setDone(false);
        tasks.updateTask(taskToBeMarked, this.taskNumber);
        response = ui.showUnmarkMessage(taskToBeMarked);

        // Assertion to ensure the task is actually unmarked
        assert !taskToBeMarked.getIsDone() : "Task should be unmarked";
    }

    /**
     * Marks the specified task as done.
     * The task list and UI are updated accordingly.
     *
     * @param taskToBeMarked The task to be marked.
     * @param tasks          The list of tasks.
     * @param ui             The UI object to interact with the user.
     * @throws LexiException If the task is already marked.
     */
    public void markTask(Task taskToBeMarked, TaskList tasks, Ui ui) throws LexiException {
        // Assertions for method preconditions
        assert taskToBeMarked != null : "Task to be marked cannot be null";
        assert tasks != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";

        if (taskToBeMarked.getIsDone()) {
            throw new LexiException("This task has already been marked!\n");
        }
        taskToBeMarked.setDone(true);
        tasks.updateTask(taskToBeMarked, this.taskNumber);
        response = ui.showMarkMessage(taskToBeMarked);

        // Assertion to ensure the task is actually marked
        assert taskToBeMarked.getIsDone() : "Task should be marked as done";
    }

    /**
     * Returns the name of the command.
     *
     * @return The string "MARK".
     */
    @Override
    public String getCommandName() {
        return "MARK";
    }

    @Override
    public String getString() {
        return response;
    }
}
