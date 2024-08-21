package exceptions.bye;

import exceptions.AhmadException;

public class ByeInvalidArgsException extends AhmadException {
  public ByeInvalidArgsException() {
    super("That is not a valid \"bye\" command");
  }
}
