package puke.commands;

import puke.TaskList;
import puke.exceptions.EmptyInputException;
import puke.exceptions.InvalidTaskNumberFormatException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.message.MessageBuilder;

/**
 * Command to mark a task as completed or incomplete based on the specified task number.
 */
public class MarkTaskCommand extends Command {
    private int taskNumber;
    private boolean isDone;

    /**
     * Constructs a MarkTaskCommand with the specified task number string and completion status.
     *
     * @param numberString the string representation of the task number to be marked.
     * @param isDone true to mark the task as completed, false to mark it as incomplete.
     * @throws InvalidTaskNumberFormatException if the numberString cannot be parsed as an integer.
     * @throws EmptyInputException if the numberString is null or empty.
     */
    public MarkTaskCommand(String numberString, boolean isDone)
            throws InvalidTaskNumberFormatException, EmptyInputException {
        if (numberString == null || numberString.trim().isEmpty()) {
            throw new EmptyInputException("You must specify a task number to mark.");
        }
        try {
            this.taskNumber = Integer.parseInt(numberString.trim());
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberFormatException();
        }
    }

    /**
     * Executes the command to mark the specified task as completed or incomplete and sends a confirmation message.
     *
     * @param taskList the TaskList that contains the task to be marked.
     * @param messageBuilder the MessageBuilder used to construct and send the confirmation message.
     * @return the confirmation message after marking the task.
     * @throws TaskNumberOutOfBoundsException if the taskNumber is outside the valid range of task numbers.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) throws TaskNumberOutOfBoundsException {
        if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
        return messageBuilder.sendMessage(taskList.markTask(taskNumber, isDone));
    }
}
