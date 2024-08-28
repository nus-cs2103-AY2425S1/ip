package puke.commands;

import puke.exceptions.EmptyInputException;
import puke.exceptions.InvalidTaskNumberFormatException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public class DeleteTaskCommand extends Command {
    private int taskNumber;

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

    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws TaskNumberOutOfBoundsException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
        messageBuilder.sendMessage(taskManager.deleteTask(taskNumber));
    }
}
