package echobot.command;

import echobot.exception.EchoBotException;

public class UndoCommand extends Command {
    public final static String COMMAND = "undo";
    private final CommandType commandType = CommandType.UNDO;

    @Override
    public CommandResponse execute() throws EchoBotException {
        if (super.commandHistoryList.isEmpty()) {
            return new CommandResponse(this.commandType, "Nothing to undo!");
        }
        Undoable taskToUndo = super.commandHistoryList.peek();
        super.commandHistoryList.pop();
        return new CommandResponse(this.commandType, taskToUndo.undo().getResponse());
    }
}
