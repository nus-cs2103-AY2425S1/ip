package chatkaki.commands;

import java.io.IOException;

public abstract class Command {
    public abstract void execute() throws IOException;
    public boolean isExit() {
        return false;
    }
}
