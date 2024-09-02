package samson;

/**
 * The <code> SamException </code> class represents exceptions specific to the Samson chatbot.
 * It extends the <code> Exception </code> class and is used to signal errors or unusual conditions
 * that occur during the execution of the application.
 */
public class SamException extends Exception {

    /**
     * Constructs a new <code> SamException </code> with the specified detail message.
     *
     * @param message The detail message that describes the error or unusual condition.
     */
    public SamException(String message) {
        super(message);
    }
}
