package joe.commands;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Prints a goodbye message.
     */
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
