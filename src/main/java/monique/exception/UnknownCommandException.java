package monique.exception;

/**
 * The <code>UnknownCommandException</code> class represents an exception that is thrown
 * when an unknown command is entered by the user. It extends the <code>MoniqueException</code>
 * class and provides specific advice related to unknown commands.
 */
public class UnknownCommandException extends MoniqueException {

    /**
     * Constructs a new <code>UnknownCommandException</code> with a default detail message.
     */
    public UnknownCommandException() {
        super("Unknown Command Exception");
    }

    /**
     * Provides advice on how to handle the exception.
     * In this case, it advises the user to check the available commands.
     */
    public void advice() {
        System.out.println("You have entered an unknown command");
        System.out.println("Please find out available commands by using '/commands'");
    }
}
