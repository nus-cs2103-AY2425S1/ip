package ahmad.exceptions.mark;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid mark arguments.
 */
public class MarkInvalidArgsException extends AhmadException {
  /**
   * Constructs a MarkInvalidArgsException instance.
   */
  public MarkInvalidArgsException() {
    super("That is not a valid \"mark\" command");
  }
}
