package ahmad.exceptions.list;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid list arguments.
 */
public class ListInvalidArgsException extends AhmadException {
  /**
   * Constructs a ListInvalidArgsException instance.
   */
  public ListInvalidArgsException() {
    super("That is not a valid \"list\" command");
  }
}
