package exceptions;

public class TodoInvalidArgsException extends AhmadException {
  public TodoInvalidArgsException() {
    super("That is an illegal Todo command >:");
  }
}
