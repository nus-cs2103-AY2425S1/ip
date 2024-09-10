package echobot.command;

import echobot.exception.EchoBotException;

public interface Undoable {
    CommandResponse undo() throws EchoBotException;
}
