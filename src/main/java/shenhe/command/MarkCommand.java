package shenhe.command;

import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;

/**
 * Represents a command to mark a task as done.
 * <p>
 * The {@code MarkCommand} class handles the command to mark a specific task as completed.
 * It validates the user input, updates the task status, and provides feedback through the user interface.
 * </p>
 */
public class MarkCommand extends Command {
    private String userInput;

    /**
     * Constructs a {@code MarkCommand} object with the specified user input.
     * <p>
     * Initializes the command with the user input, which specifies the task to be marked as done.
     * </p>
     *
     * @param userInput The full user input to be processed, which should include the task number to mark as done.
     */
    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the mark command to update the status of a task to be done.
     * <p>
     * This method extracts the task number from the user input and marks the corresponding task as done.
     * It throws an {@link EmptyTaskDescriptionException} if the input is insufficient. After marking the task,
     * it updates the task list and provides feedback through the {@link Ui} instance. The updated tasks are saved
     * using the {@link Storage} instance.
     * </p>
     *
     * @param tasks The current task list containing all tasks.
     * @param ui The user interface instance used for displaying messages to the user.
     * @param storage The storage instance used for saving tasks after updating.
     * @throws EmptyTaskDescriptionException If the user input does not contain a task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 4) {
            throw new EmptyTaskDescriptionException();
        } else {
            int taskNumber = Integer.parseInt(userInput.substring(4).trim());
            if (taskNumber >= 1 && taskNumber <= tasks.getSize()) {
                tasks.getTask(taskNumber - 1).markAsDone();
                ui.showMarkMessage();
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
