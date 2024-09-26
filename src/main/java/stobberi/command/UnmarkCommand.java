package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.NoNumberStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to mark a task as incomplete in a {@link TaskList}.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs a new {@code UnmarkCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList      The list of tasks where the task will be marked as incomplete.
     * @param restOfCommand The rest of the command given, which should include the task number.
     */
    public UnmarkCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by marking the specified task as incomplete in the {@link TaskList}.
     * If the task number is not provided or if more than one number is provided, a {@link NoNumberStobberiException}
     * is thrown.
     *
     * @return A string indicating that the task has been marked as incomplete.
     * @throws StobberiException If the task number is missing or invalid.
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
            throw new NoNumberStobberiException("Please put only 1 number after the unmark command.");
        }

        return getTaskList().unmarkTask(taskNumber);
    }
}
