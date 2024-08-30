package Exceptions;

/**
 * An exception that occurs when the parser cannot recognise the userInput
 */
public class CommandNotRecognisedException extends TestamentException {

    /**
     * Constructor for CommandNotRecognisedException
     */
    public CommandNotRecognisedException() {
        super("Apologies, but I do not understand that command");
    }

    /**
     * Constructor for CommandNotRecognisedException
     *
     * @param s Message for exception
     */
    public CommandNotRecognisedException(String s) {
        super(s);
    }

}
