package exceptions.todo;

import exceptions.AhmadException;

public class TodoEmptyNameException extends AhmadException {
  public TodoEmptyNameException() {
    super("Name should not be empty");
  }
}
