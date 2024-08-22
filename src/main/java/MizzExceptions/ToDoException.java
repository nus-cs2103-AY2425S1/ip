package MizzExceptions;

import util.Utility;

public class ToDoException extends MizzException {
  public ToDoException(String msg) {
    super(msg);
  }

  @Override
  public String toString() {
    return Utility.INDENT + "Uh oh what did you to the to do?: "
        + super.getMessage() + "\n"
        + Utility.INDENT + "Example usage: todo <description>";
  }
}
