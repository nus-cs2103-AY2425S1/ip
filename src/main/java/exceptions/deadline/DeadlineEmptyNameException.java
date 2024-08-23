package exceptions.deadline;

import exceptions.AhmadException;

public class DeadlineEmptyNameException extends AhmadException {
  public DeadlineEmptyNameException() {
    super("Name should not be empty");
  }
}
