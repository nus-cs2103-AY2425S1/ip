package puke.commands;

import puke.exceptions.EmptyInputException;
import puke.exceptions.InvalidTaskNumberFormatException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public class MarkTaskCommand extends Command {
    private int taskNumber;
    private boolean isDone;

    public MarkTaskCommand(String numberString, boolean isDone) throws InvalidTaskNumberFormatException, EmptyInputException {
        if (numberString.isEmpty()) {
            throw new EmptyInputException("You must specify a task number to mark.");
        }
        try {
            this.taskNumber = Integer.parseInt(numberString.trim());
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberFormatException();
        }
    }

    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws TaskNumberOutOfBoundsException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
        messageBuilder.sendMessage(taskManager.markTask(taskNumber, isDone));
    }
}
