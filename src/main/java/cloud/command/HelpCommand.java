package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to display help information.
 * A <code>HelpCommand</code> object can either show a general help message
 * or detailed help for a specific command.
 */
public class HelpCommand extends Command {
    private final CommandType commandType;

    public HelpCommand() {
        this.commandType = null;
    }

    public HelpCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(commandType);
        return ui.showHelp(commandType);
    }
}
