package sentinel.command;

import sentinel.exception.SentinelException;
import sentinel.ui.Ui;
import sentinel.utils.SentinelList;
import sentinel.Sentinel;

public abstract class Command {
    protected Ui ui;
    protected SentinelList list;

    public Command(Ui ui, SentinelList list) {
        this.ui = ui;
        this.list = list;
    }

    public abstract void execute(String input) throws SentinelException;

    public static Command createCommand(Sentinel.CommandType commandType, Ui ui, SentinelList list) {
        return switch (commandType) {
            case list -> new ListCommand(ui, list);
            case mark, unmark, delete -> new ModifyCommand(ui, list, commandType);
            case todo, deadline, event -> new CreateTaskCommand(ui, list, commandType);
            case help -> new HelpCommand(ui, list);
            case bye -> new ByeCommand(ui, list);
            default -> throw new IllegalArgumentException("Unknown sentinel.command");
        };
    }
}