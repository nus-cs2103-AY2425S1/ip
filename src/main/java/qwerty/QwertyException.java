package qwerty;

/**
 * This class encapsulates exceptions unique to the Qwerty chatbot.
 * An example is missing or invalid arguments.
 */
public class QwertyException extends Exception {
    public QwertyException(String message) {
        super(message);
    }
}
