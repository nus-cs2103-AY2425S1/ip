package echobot.command;

import echobot.exception.EchoBotException;

/**
 * Undoable interface.
 */
public interface Undoable {
    /**
     * Undo the command.
     *
     * @return CommandResponse.
     * @throws EchoBotException If the undo throws an EchoBotException.
     */
    CommandResponse undo() throws EchoBotException;
}
