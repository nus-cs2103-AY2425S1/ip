package puke.commands;

import puke.TaskList;
import puke.exceptions.EmptyInputException;
import puke.exceptions.InvalidTaskNumberFormatException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.message.MessageBuilder;

/**
 * Command to delete a task by its task number.
 */
public class DeleteTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteTaskCommand with the specified task number string.
     *
     * @param numberString the string representation of the task number to be deleted.
     * @throws InvalidTaskNumberFormatException if the numberString cannot be parsed as an integer.
     * @throws EmptyInputException if the numberString is null or empty.
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
     * Executes the command to delete the specified task from the TaskList and sends a confirmation message.
     *
     * @param taskList the TaskList from which the task will be deleted.
     * @param messageBuilder the MessageBuilder used to construct and send the confirmation message.
     * @return the confirmation message after deleting the task.
     * @throws TaskNumberOutOfBoundsException if the taskNumber is outside the valid range of task numbers.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) throws TaskNumberOutOfBoundsException {
        if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
        return messageBuilder.sendMessage(taskList.deleteTask(taskNumber));
    }
}
