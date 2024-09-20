package exceptions;

/**
 * The class `CommandNotFoundException` extends `Exception` and provides a custom message for when a command
 * is not found in a library.
 */
public class CommandNotFoundException extends Exception {
    @Override
    public String toString() {
        return "command not found in lib";
    }
}
