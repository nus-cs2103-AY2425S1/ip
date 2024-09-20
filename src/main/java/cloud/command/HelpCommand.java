package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

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
        return ui.showHelp(commandType);
    }
}
