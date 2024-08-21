package exceptions.mark;

import exceptions.AhmadException;

public class MarkIndexOutOfBoundsException extends AhmadException {
  public MarkIndexOutOfBoundsException(String number) {
    super("\"" + number + "\" is out of bounds!");
  }
}
