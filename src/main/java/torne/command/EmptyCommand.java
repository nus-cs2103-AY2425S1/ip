package torne.command;

import java.util.Map;

/**
 * Empty command that does nothing.
 */
public class EmptyCommand extends Command {
    protected EmptyCommand() {
        super("");
    }
    
    @Override
    public void execute(Map<String, String> arguments) {

    }
}
