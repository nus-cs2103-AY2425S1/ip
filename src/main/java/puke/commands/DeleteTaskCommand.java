package puke.commands;

import puke.exceptions.EmptyInputException;
import puke.exceptions.InvalidTaskNumberFormatException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

/**
 * Command to delete a task by its task number.
 */
public class DeleteTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteTaskCommand with the specified task number string.
     *
     * @param numberString the string representation of the task number to be deleted
     * @throws InvalidTaskNumberFormatException if the numberString cannot be parsed as an integer
     * @throws EmptyInputException if the numberString is null or empty
     */
    public DeleteTaskCommand(String numberString) throws InvalidTaskNumberFormatException, EmptyInputException {
        if (numberString == null || numberString.trim().isEmpty()) {
            throw new EmptyInputException("OOPS!!! You must specify a task number to delete!!");
        }
        try {
            this.taskNumber = Integer.parseInt(numberString.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberFormatException();
        }
    }

    /**
     * Executes the command to delete the specified task from the TaskManager and sends a confirmation message.
     *
     * @param taskManager the TaskManager from which the task will be deleted
     * @param messageBuilder the MessageBuilder used to construct and send a confirmation message
     * @throws TaskNumberOutOfBoundsException if the taskNumber is outside the valid range of task numbers
     */
    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws TaskNumberOutOfBoundsException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
        messageBuilder.sendMessage(taskManager.deleteTask(taskNumber));
    }
}
