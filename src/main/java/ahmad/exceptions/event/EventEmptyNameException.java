package ahmad.exceptions.event;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for empty event names.
 */
public class EventEmptyNameException extends AhmadException {
  /**
   * Constructs an EventEmptyNameException instance.
   */
  public EventEmptyNameException() {
    super("Name should not be empty");
  }
}
