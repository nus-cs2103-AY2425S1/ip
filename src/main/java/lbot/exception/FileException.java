package lbot.exception;

/**
 * This exception is thrown when an issue is encountered while reading/writing to file.
 */
public class FileException extends LBotException {

    public FileException(String message) {
        super(message);
    }
}
