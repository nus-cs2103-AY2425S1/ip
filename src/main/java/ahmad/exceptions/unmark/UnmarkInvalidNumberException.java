package ahmad.exceptions.unmark;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid unmark number.
 */
public class UnmarkInvalidNumberException extends AhmadException {
  /**
   * Constructs an UnmarkInvalidNumberException instance based on given number.
   *
   * @param number The invalid number in question.
   */
  public UnmarkInvalidNumberException(String number) {
    super("\"" + number + "\" is an invalid number");
  }
}
