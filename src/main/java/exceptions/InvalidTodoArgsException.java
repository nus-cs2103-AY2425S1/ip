package exceptions;

public class InvalidTodoArgsException extends Exception {
  public InvalidTodoArgsException() {
    super("That is an illegal Todo command >:");
  }
}
