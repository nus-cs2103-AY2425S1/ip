package executable;

/**
 * An exit executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Exit extends Executable {
    /**
     * Output an exit message.
     *
     * @return 1.
     */
    @Override
    public int execute() {
        super.output("Bye. Hope to see you again soon!");
        return 1;
    }
}
