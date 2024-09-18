package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.NoNumberStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to delete a task from a {@link TaskList} based on the task number.
 */
public class DeleteCommand extends Command {
    /**
     * The list of tasks from which the task will be deleted.
     */
    private TaskList taskList;

    /**
     * The rest of the command given.
     */
    private String restOfCommand;

    /**
     * Constructs a new {@code DeleteCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList   The list of tasks from which the task will be deleted.
     * @param restOfCommand The rest of the command given.
     */
    public DeleteCommand(TaskList taskList, String restOfCommand) {
        this.taskList = taskList;
        this.restOfCommand = restOfCommand;
    }

    /**
     * Executes the command by deleting the task from the {@link TaskList} based on the task number.
     */
    @Override
    public String execute() throws StobberiException {
        int taskNumber;
        boolean b = restOfCommand.matches("\\d+");
        String[] parts = restOfCommand.split(" ");

        if (b) {
            taskNumber = Integer.parseInt(restOfCommand);
        } else if (!b && parts.length == 1) {
            throw new NoNumberStobberiException("Where is the number???");
        } else {
            throw new NoNumberStobberiException("Please put only 1 number after the exit command.");
        }

        return taskList.delete(taskNumber);
    }
}
