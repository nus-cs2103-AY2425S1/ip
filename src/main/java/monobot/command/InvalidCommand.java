package monobot.command;

import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printError("Invalid monobot.command.Command. Valid commands are: \n" +
                "list, todo, deadline, event, mark, unmark, bye");
    }
}
