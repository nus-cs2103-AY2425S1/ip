package nixy.command;

/**
 * Genric Command interface represents the different commands that the user can input.
 */
public interface Command {
    CommandType getType();
    void execute();
}
