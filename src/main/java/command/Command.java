package command;

import utility.TaskList;
import utility.Storage;
import exception.ElliotException;

public abstract class Command {

    protected Command() {}

    public abstract Command parseArguments(String unparsedArguments) throws ElliotException;

    public abstract TaskList runCommand(TaskList taskList, Storage storage) throws ElliotException;

}
