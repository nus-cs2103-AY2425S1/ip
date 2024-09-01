package ahmad.exceptions.delete;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid delete arguments.
 */
public class DeleteInvalidArgsException extends AhmadException {
  /**
   * Constructs a DeleteInvalidArgsException instance.
   */
  public DeleteInvalidArgsException() {
    super("That is not a valid \"delete\" command");
  }
}
