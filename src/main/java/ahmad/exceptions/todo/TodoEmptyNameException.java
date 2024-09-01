package ahmad.exceptions.todo;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for empty todo name.
 */
public class TodoEmptyNameException extends AhmadException {
  /**
   * Constructs a TodoEmptyNameException instance.
   */
  public TodoEmptyNameException() {
    super("Name should not be empty");
  }
}
