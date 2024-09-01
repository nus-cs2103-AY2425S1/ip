package ahmad.exceptions.event;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid event arguments.
 */
public class EventInvalidArgsException extends AhmadException {
  /**
   * Constructs an EventInvalidArgsException instance.
   */
  public EventInvalidArgsException() {
    super("That is not a valid \"event\" command");
  }
}
