package exceptions.delete;

import exceptions.AhmadException;

public class DeleteInvalidArgsException extends AhmadException {
  public DeleteInvalidArgsException() {
    super("That is not a valid \"delete\" command");
  }
}
