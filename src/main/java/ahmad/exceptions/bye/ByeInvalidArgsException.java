package ahmad.exceptions.bye;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for bye command
 */
public class ByeInvalidArgsException extends AhmadException {
  /**
   * Constructs a ByeInvalidArgsException instance.
   */
  public ByeInvalidArgsException() {
    super("That is not a valid \"bye\" command");
  }
}
