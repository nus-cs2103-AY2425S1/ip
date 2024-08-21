package exceptions.unmark;

import exceptions.AhmadException;

public class UnmarkInvalidArgsException extends AhmadException {
  public UnmarkInvalidArgsException() {
    super("That is not a valid mark command");
  }
}
