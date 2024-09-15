package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command that handles unrecognized or unknown commands.
 * The {@code UnknownCommand} throws a {@link KukiShinobuException} when executed,
 * indicating that the provided command could not be understood.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the unknown command.
     * This method throws a {@link KukiShinobuException} with a message indicating
     * that the command is not recognized or understood.
     *
     * @param taskList The task list, which is not used in this command.
     * @param storage  The storage object, which is not used in this command.
     * @throws KukiShinobuException Always thrown to indicate an unknown command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KukiShinobuException {
        throw new KukiShinobuException("Hmm... I don't quite understand what you mean!");
    }
}
