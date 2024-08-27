package gray.command_factory;

import gray.GrayException;
import gray.command.ByeCommand;
import gray.command.Command;

public class ByeCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        if (!text.equals("bye")) return null;
        return new ByeCommand();
    }
}
