package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.NoNumberStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to delete a task from a {@link TaskList} based on the task number.
 * The task number is provided as part of the command.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new {@code DeleteCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList      The list of tasks from which the task will be deleted.
     * @param restOfCommand The string containing the task number to be deleted.
     */
    public DeleteCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by deleting the task from the {@link TaskList} based on the task number.
     * If the task number is not provided or is not valid, a {@link NoNumberStobberiException} is thrown.
     *
     * @return A string indicating the successful deletion of the task.
     * @throws StobberiException If the task number is missing, invalid, or an error occurs during deletion.
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
            throw new NoNumberStobberiException("Please put only 1 number after the exit command.");
        }

        return getTaskList().delete(taskNumber);
    }
}
