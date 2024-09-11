package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            return ui.printFindTasksMsg(taskList, parseFindCommand());
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }
    }

    public String parseFindCommand() throws WrongFormatException {
        String[] input = message.split("\\s+");
        if (input.length != 2) {
            throw new WrongFormatException("find", "Find command should be in the format of \"find [a SINGLE keyword]\"");
        }

        return input[1];
    }
}
