package mortalreminder.errorhandling;

/**
 * Creates a throwable exception class that is specific to this project.
 */
public class MortalReminderException extends Exception {
    public MortalReminderException(String message) {
        super(message);
    }
}
