package Alex.Command;

/**
 * Base class for all commands that provides default implementations.
 */
public abstract class CommandBase implements Command {
    protected boolean isExit = false;

    @Override
    public boolean isExit() {
        return isExit;
    }
}
