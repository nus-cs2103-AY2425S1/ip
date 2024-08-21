package exceptions.list;

import exceptions.AhmadException;

public class ListInvalidArgsException extends AhmadException {
  public ListInvalidArgsException() {
    super("That is not a valid \"list\" command");
  }
}
