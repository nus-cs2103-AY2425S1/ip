package twilight;

/**
 * Represents a Command which tells the program the user has requested to close.
 */
public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }
}
