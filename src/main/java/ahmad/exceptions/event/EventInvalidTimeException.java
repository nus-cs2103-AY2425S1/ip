package ahmad.exceptions.event;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for invalid event times.
 */
public class EventInvalidTimeException extends AhmadException {
  /**
   * Constructs an EventInvalidTimeException instance based on given time.
   *
   * @param time The invalid time in question.
   */
  public EventInvalidTimeException(String time) {
    super(time + " is not a valid event time");
  }
}
