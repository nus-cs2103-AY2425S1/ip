package ipman.commands;

/**
 * Represents a particular command that the user wants to execute.
 * Implement this interface and provide all the arguments through the
 * constructor to add a new command.
 */
public interface Command {
    void execute(Context context);
}
