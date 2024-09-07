package command;

import exception.ElliotException;
import utility.Storage;
import utility.TaskList;

/**
 * Abstract class as a boilerplate for all user commands.
 */
public abstract class Command {

    /**
     * Abstract method for classes to implement how arguments are to be parsed.
     *
     * @param unparsedArguments string representing the unparsed form of the command argument.
     * @return A new {@link Command} with the properly parsed arguments.
     * @throws ElliotException If command arguments are invalid or incomplete.
     */
    public abstract Command parseArguments(String unparsedArguments) throws ElliotException;

    /**
     * Abstract method for classes to implement the actions this command will perform.
     *
     * @param taskList the {@link TaskList} at current stage.
     * @param storage the {@link Storage} file of which the save file is stored in.
     * @return {@link TaskList} which may or may not have undergone modification within function.
     * @throws ElliotException If command arguments are invalid or incomplete.
     */
    public abstract TaskList runCommand(TaskList taskList, Storage storage) throws ElliotException;

}
