package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

/**
 * Represents a Command of type FindCommand (find tasks containing a specific keyword).
 *
 * @author Adapted from Feng1231.
 */
public class FindCommand extends Command {
    /**
     * Constructs a FindCommand instance.
     *
     * @param message The whole command input in String.
     */
    public FindCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        try {
            return ui.printFindTasksMsg(taskList, parseFindCommand());
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }
    }

    /**
     * Interprets a command of type find.
     *
     * @return A specific keyword used to search if the command is valid.
     * @throws WrongFormatException If the command is in wrong format.
     */
    public String parseFindCommand() throws WrongFormatException {
        String[] input = message.split("\\s+");
        if (input.length != 2) {
            throw new WrongFormatException("find", "Find command should be in the format of \"find [a SINGLE keyword]\"");
        }

        return input[1];
    }
}
