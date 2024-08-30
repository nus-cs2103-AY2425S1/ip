package shenhe.command;

import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;

/**
 * Represents a command to unmark a task as not done in the task list.
 * <p>
 * The {@code UnmarkCommand} class handles the command to update a task's status from done to not done
 * based on the task number provided by the user. It validates the input, updates the task's status, and
 * provides feedback through the user interface.
 * </p>
 */
public class UnmarkCommand extends Command {
    private String userInput;

    /**
     * Constructs an {@code UnmarkCommand} object with the specified user input.
     * <p>
     * Initializes the command with the user input, which specifies the task number of the task to be unmarked.
     * </p>
     *
     * @param userInput The full user input to be processed, which should include the task number.
     */
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     * <p>
     * This method extracts the task number from the user input and updates the status of the corresponding task
     * to not done. It throws an {@link EmptyTaskDescriptionException} if the input does not contain a valid task number.
     * After updating the task, it provides feedback through the {@link Ui} instance and saves the updated tasks
     * using the {@link Storage} instance.
     * </p>
     *
     * @param tasks The current task list where the task's status will be updated.
     * @param ui The user interface instance used for displaying messages to the user.
     * @param storage The storage instance used for saving tasks after updating.
     * @throws EmptyTaskDescriptionException If the user input does not contain a valid task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 6) {
            throw new EmptyTaskDescriptionException();
        } else {
            int taskNumber = Integer.parseInt(userInput.substring(6).trim());
            if (taskNumber >= 1 && taskNumber <= tasks.getSize()) {
                ui.showUnmarkMessage();
                tasks.getTask(taskNumber - 1).markAsUndone();
                System.out.println(tasks.getTask(taskNumber - 1).toString());
                storage.saveTasks(tasks);
            }
        }
    }

    /**
     * Indicates whether this command should exit the application.
     * <p>
     * This method returns {@code false} to signal that the application should continue running.
     * </p>
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
