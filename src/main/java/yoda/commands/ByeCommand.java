package yoda.commands;

/**
 * Represents a command that handles the termination of the session.
 * When executed, it prints a goodbye message to the console.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand by printing a goodbye message to the console.
     * This message indicates the end of the session.
     *
     * @return
     */
    public String run() {
        return "Bye. See you again soon, I hope to.";
    }
}
