package command;

import storage.Storage;
import task.TaskList;

/**
 * Represents a command that is invoked when an unrecognized or unknown command is issued by the user.
 * The {@code UnknownCommand} is used to notify the user that the input command could not be understood.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the unknown command.
     * This method provides feedback to the user, indicating that the command is not recognized.
     * It returns a message suggesting that the input was not understood.
     * The {@code taskList} and {@code storage} objects are not utilized in this command.
     *
     * @param taskList The task list, which is not used in this command.
     * @param storage  The storage object, which is not used in this command.
     * @return A string message informing the user that the command is not recognized.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Hmm... I don't quite understand what you mean!";
    }
}
