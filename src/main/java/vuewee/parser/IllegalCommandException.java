package vuewee.parser;

import vuewee.task.Task;

public class IllegalCommandException extends IllegalArgumentException {

  public IllegalCommandException() {
    super();
  }

  public IllegalCommandException(String message) {
    super(message);
  }

  public IllegalCommandException(Task task, boolean isDone) {
    super(
        (isDone ? "This task is already done:\n  " : "This task is already marked as not done:\n  ") + task.toString());
    ;
  }
}
