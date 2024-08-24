package spongebob.exception;

/**
 * Exception used when there is user input errors in Spongebob
 */
public class SpongebobException extends Exception{

    public SpongebobException(String text) {
        super(text);
    }
}
