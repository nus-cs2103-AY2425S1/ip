package nether.command;

import nether.task.Task;

/**
 * Represents a command to mark a task as not done.
 * <p>
 * The {@code MarkNotDoneCommand} class is a subclass of {@code MarkCommand} and provides the implementation
 * for marking a task as incomplete or not done.
 * </p>
 */
public class MarkNotDoneCommand extends MarkCommand {

    /**
     * Constructs a {@code MarkNotDoneCommand} with the specified task number.
     *
     * @param taskNumber The index of the task to be marked as not done.
     */
    public MarkNotDoneCommand(int taskNumber) {
        super(taskNumber);
    }

    /**
     * Marks the specified task as not done.
     * <p>
     * This method sets the status of the task to not done by calling the {@code markAsNotDone} method on the task.
     * </p>
     *
     * @param taskToMark The task to be marked as not done.
     */
    @Override
    public void markTask(Task taskToMark) {
        taskToMark.markAsNotDone();
    }

    /**
     * Returns the message to be displayed to the user after marking a task as not done.
     * <p>
     * This method provides a message indicating that the task has been successfully marked as not done.
     * </p>
     *
     * @return The message "Understood, I've marked this task as not done:".
     */
    @Override
    public String getMarkMessage() {
        return "Understood, I've marked this task as not done:";
    }
}