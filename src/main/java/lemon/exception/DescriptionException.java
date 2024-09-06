package lemon.exception;
/**
 * Exception class for missing descriptions of tasks
 * Used for tasks to prevent empty descriptions
 * @author He Yiheng
 */
public class DescriptionException extends Exception {
    public DescriptionException(String msg) {
        super(msg);
    }
}
