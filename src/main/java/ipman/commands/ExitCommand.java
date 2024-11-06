package ipman.commands;

/**
 * Throws an exception to signal that the application should exit
 *
 * @see ExitException
 */
public class ExitCommand implements Command {
    @Override
    public void execute(Context context) {
        throw new ExitException();
    }
}
