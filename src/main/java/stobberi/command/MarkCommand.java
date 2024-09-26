package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.NoNumberStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to mark a task as completed in a {@link TaskList}.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a new {@code MarkCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList      The list of tasks where the task will be marked as completed.
     * @param restOfCommand The rest of the command given, which should include the task number.
     */
    public MarkCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by marking the specified task as completed in the {@link TaskList}.
     * The task number must be provided and should be a valid integer.
     * If the command does not contain a valid number, an {@link NoNumberStobberiException} is thrown.
     *
     * @return A string indicating the task has been marked as completed.
     * @throws StobberiException If the command does not include a valid task number or other errors occur.
     */
    @Override
    public String execute() throws StobberiException {
        int taskNumber;
        String restOfCommand = getRestOfCommand();
        String[] parts = restOfCommand.split(" ");

        if (restOfCommand.matches("\\d+")) {
            taskNumber = Integer.parseInt(restOfCommand);
        } else if (parts.length == 1) {
            throw new NoNumberStobberiException("Where is the number???");
        } else {
            throw new NoNumberStobberiException("Please put only 1 number after the mark command.");
        }

        return getTaskList().markTask(taskNumber);
    }
}
