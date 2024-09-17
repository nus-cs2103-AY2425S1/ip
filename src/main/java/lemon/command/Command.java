package lemon.command;

import lemon.Lemon;

public abstract class Command {
    protected CommandType ct;

    public Command(CommandType ct) {
        this.ct = ct;
    }

    public abstract void run(Lemon lemonInstance);
}
