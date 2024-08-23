package exceptions.delete;

import exceptions.AhmadException;

public class DeleteInvalidNumberException extends AhmadException {
  public DeleteInvalidNumberException(String number) {
    super("\"" + number + "\" is an invalid number");
  }
}
