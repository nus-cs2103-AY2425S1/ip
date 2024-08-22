package MizzExceptions;

import util.Utility;

public class UpdateMarkedException extends MizzException {
  public UpdateMarkedException(String msg) {
    super(msg);
  }

  @Override
  public String toString() {
    return Utility.INDENT + "Thats bad marking >:( "
        + super.getMessage() + "\n"
        + Utility.INDENT + "Example usage: mark <valid_idx_from_1>\n"
        + Utility.INDENT + "Example usage: unmark <valid_idx_from_1>";
  }
}
