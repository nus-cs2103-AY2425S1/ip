package nether.command;

import nether.task.Task;

/**
 * Represents a command to mark a task as done.
 * <p>
 * The {@code MarkDoneCommand} class extends {@code MarkCommand} and provides the specific implementation
 * for marking a task as completed.
 * </p>
 */
public class MarkDoneCommand extends MarkCommand {
    /**
     * Constructs a {@code MarkDoneCommand} with the specified task number.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public MarkDoneCommand(int taskNumber) {
        super(taskNumber);
    }

    /**
     * Marks the specified task as done.
     * <p>
     * This method sets the status of the task to done by calling the {@code markAsDone} method on the task.
     * </p>
     *
     * @param taskToMark The task to be marked as done.
     */
    @Override
    public void markTask(Task taskToMark) {
        taskToMark.markAsDone();
    }

    /**
     * Returns the message to be displayed to the user after marking a task as done.
     * <p>
     * This method provides a message indicating that the task has been successfully marked as done.
     * </p>
     *
     * @return The message "Well done! I've marked this task as done:".
     */
    @Override
    public String getMarkMessage() {
        return "Well done! I've marked this task as done:";
    }
}
