package echoa.command;

import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

/**
 * ByeCommand is a class which encapsulates Bye Commands.
 * It extends from Command.
 */
public class ByeCommand extends Command {
    public ByeCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Executes the command by doing nothing.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) {
        // do nothing
    }

    /**
     * Returns Echoa's reply to the Bye Command
     *
     * @return a String message of Echoa's reply to the Bye Command.
     */
    @Override
    public String getMessage() {
        return ui.getEndMessage();
    }
}
