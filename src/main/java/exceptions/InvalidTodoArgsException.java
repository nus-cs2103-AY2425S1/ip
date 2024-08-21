package exceptions;

public class InvalidTodoArgsException extends AhmadException {
  public InvalidTodoArgsException() {
    super("That is an illegal Todo command >:");
  }
}
