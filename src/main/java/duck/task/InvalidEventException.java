package duck.task;

/**
 * Represents an exception thrown to indicate that the format of deadline is wrong.
 */
public class InvalidEventException extends Exception {
  /**
   * Constructs new InvalidEventException with specified message.
   */
  public InvalidEventException(String message) {
    super(message);
  }
}
