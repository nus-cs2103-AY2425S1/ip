package exceptions;

public class InvalidCommandUsageException extends Exception {
    public InvalidCommandUsageException(String usage) {
        super("Invalid command. Usage: " + usage);
    }
}
