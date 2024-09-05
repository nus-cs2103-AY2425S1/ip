package meep;

/**
 * The {@code MeepException} class represents exceptions that are specific to the Meep application.
 * It extends the {@code Exception} class and provides custom error messages
 * for exceptions that occur within the application.
 */
public class MeepException extends Exception {
    public MeepException(String message) {
        super(message);
    }
}
