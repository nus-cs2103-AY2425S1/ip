package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.InvalidInputStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to display all tasks in a {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new {@code ListCommand} with the specified {@link TaskList}.
     *
     * @param taskList      The list of tasks to be displayed.
     * @param restOfCommand The rest of the command given (though not used in this command).
     */
    public ListCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by displaying all tasks in the {@link TaskList}.
     * If there is any additional text in the command, an {@link InvalidInputStobberiException} is thrown
     * with a message suggesting to remove unnecessary input.
     *
     * @return A string containing the list of all tasks.
     * @throws StobberiException If additional text is provided in the command.
     */
    @Override
    public String execute() throws StobberiException {
        if (getRestOfCommand().isEmpty()) {
            return getTaskList().displayList();
        }
        throw new InvalidInputStobberiException("Do you mean 'list'? "
                + "Pwease don't add unnecessary stuff!");
    }
}
