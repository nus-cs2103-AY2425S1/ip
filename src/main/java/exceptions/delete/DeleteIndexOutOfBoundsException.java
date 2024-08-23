package exceptions.delete;

import exceptions.AhmadException;

public class DeleteIndexOutOfBoundsException extends AhmadException {
  public DeleteIndexOutOfBoundsException(String number) {
    super("\"" + number + "\" is out of bounds!");
  }
}
