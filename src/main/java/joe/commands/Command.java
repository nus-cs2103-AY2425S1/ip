package joe.commands;

import joe.exceptions.InvalidCommandException;

public abstract class Command {
    public abstract void execute() throws IllegalArgumentException, InvalidCommandException;

    public boolean isBye() {
        return false;
    }
}
