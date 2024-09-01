package ahmad.exceptions.delete;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for out of bounds index in a delete command.
 */
public class DeleteIndexOutOfBoundsException extends AhmadException {
  /**
   * Constructs a DeleteIndexOutOfBoundsException instance.
   *
   * @param number The invalid index in question.
   */
  public DeleteIndexOutOfBoundsException(String number) {
    super("\"" + number + "\" is out of bounds!");
  }
}
