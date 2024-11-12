package lemon.exception;
/**
 * Exception class for invalid format of commands
 * Used for commands dependent on multiple specific inputs
 * @author He Yiheng
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
