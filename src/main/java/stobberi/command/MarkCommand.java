package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.NoNumberStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to mark a task as completed in a {@link TaskList}.
 */
public class MarkCommand extends Command {
    /**
     * The list of tasks where the task will be marked as completed.
     */
    private TaskList taskList;
    /**
     * The rest of the command given.
     */
    private String restOfCommand;

    /**
     * Constructs a new {@code MarkCommand} with the specified {@link TaskList} and task number.
     *
     * @param taskList   The list of tasks where the task will be marked as completed.
     * @param restOfCommand The rest of command given.
     */
    public MarkCommand(TaskList taskList, String restOfCommand) {
        this.taskList = taskList;
        this.restOfCommand = restOfCommand;
    }

    /**
     * Executes the command by marking the specified task as completed in the {@link TaskList}.
     */
    @Override
    public String execute() throws StobberiException {
        int taskNumber;
        String[] parts = restOfCommand.split(" ");

        if (restOfCommand.matches("\\d+")) {
            taskNumber = Integer.parseInt(restOfCommand);
        } else if (parts.length == 1) {
            throw new NoNumberStobberiException("Where is the number???");
        } else {
            throw new NoNumberStobberiException("Please put only 1 number after the exit command.");
        }

        return taskList.markTask(taskNumber);
    }
}
