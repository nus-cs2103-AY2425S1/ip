package echoa.command;

import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

/**
 * HiCommand is a class which encapsulates Hi Commands.
 * It extends from Command.
 */
public class HiCommand extends Command {
    public HiCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
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
     * Returns Echoa's reply to the Hi Command
     *
     * @return a String message of Echoa's reply to the Hi Command.
     */
    @Override
    public String getMessage() {
        return ui.getStartMessage();
    }
}
