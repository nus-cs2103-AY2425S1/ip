/**
 * Custom exception class to represent specific exceptions related to SilverWolf chat bot.
 */
public class SilverWolfException extends Exception{

    /**
     * Constructs a new SilverWolfException with the specified detail message.
     *
     * @param message The detail message, providing more information about the exception.
     */
    public SilverWolfException(String message){
        super(message);
    }
}
