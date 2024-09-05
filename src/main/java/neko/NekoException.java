package neko;

/**
 * The NekoException class represents a custom exception for the Neko application.
 * It extends the built-in Java {@code Exception} class and prefixes exception messages
 * with a cute cat-like character string to maintain the persona of the application.
 */
public class NekoException extends Exception {

    /**
     * Constructs a new NekoException with a custom error message.
     * The message is prefixed with a cat-like emoticon.
     *
     * @param msg The custom error message.
     */
    public NekoException(String msg) {
        super("Meow /ᐠ > ˕ <マ " + msg);
    }

}
