package MizzExceptions;

import util.Utility;

public class UpdateMarkedException extends MizzException {
  private final boolean mark;

  public UpdateMarkedException(String msg, String mark) {
    super(msg);
    this.mark = mark.equals("mark");
  }

  @Override
  public String toString() {
    return Utility.INDENT + "Thats bad marking >:( "
        + super.getMessage() + "\n"
        + (this.mark
            ? Utility.INDENT + "Example usage: mark <valid_idx_from_1>"
            : Utility.INDENT + "Example usage: unmark <valid_idx_from_1>");
  }
}
