package garfield.commands;

import garfield.commands.Command;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }
}
