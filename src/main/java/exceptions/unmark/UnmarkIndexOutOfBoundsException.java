package exceptions.unmark;

import exceptions.AhmadException;

public class UnmarkIndexOutOfBoundsException extends AhmadException {
  public UnmarkIndexOutOfBoundsException(String number) {
    super("\"" + number + "\" is out of bounds!");
  }
}
