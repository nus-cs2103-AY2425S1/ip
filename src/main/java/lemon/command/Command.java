package lemon.command;

import lemon.Lemon;

/**
 * Parent abstract class for commands that lemon can execute
 * @author He Yiheng
 */
public abstract class Command {
    private CommandType ct;

    /**
     * Constructor for Command
     * @param ct stores the enum {@link CommandType}
     */
    public Command(CommandType ct) {
        this.ct = ct;
    }

    public CommandType getCommandType() {
        return ct;
    }

    /**
     * Method called to execute the command
     * @param lemonInstance {@link Lemon} instance where the command will execute on
     */
    public abstract void run(Lemon lemonInstance);
}
