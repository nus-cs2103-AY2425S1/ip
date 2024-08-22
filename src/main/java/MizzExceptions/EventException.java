package MizzExceptions;

import util.Utility;

public class EventException extends MizzException {
  public EventException(String msg) {
    super(msg);
  }

  @Override
  public String toString() {
    return Utility.INDENT + "Oops! My advanced training hasn't prepared me for this event!: "
        + super.getMessage() + "\n"
        + Utility.INDENT + "Example usage: event <description> /from <from> /to <to>";
  }
}
