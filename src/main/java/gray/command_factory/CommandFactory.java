package gray.command_factory;

import gray.GrayException;
import gray.command.Command;

public abstract class CommandFactory {
    public abstract Command parse(String text) throws GrayException;
}
