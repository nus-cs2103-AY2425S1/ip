package exceptions.deadline;

import exceptions.AhmadException;

public class DeadlineInvalidArgsException extends AhmadException {
  public DeadlineInvalidArgsException() {
    super("That is not a valid \"deadline\" command");
  }
}
