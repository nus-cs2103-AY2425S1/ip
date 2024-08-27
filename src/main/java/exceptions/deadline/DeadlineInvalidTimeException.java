package exceptions.deadline;

import exceptions.AhmadException;

public class DeadlineInvalidTimeException extends AhmadException {
  public DeadlineInvalidTimeException(String time) {
    super(time + " is not a valid deadline time");
  }
}
