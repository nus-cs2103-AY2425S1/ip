package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
    }
}