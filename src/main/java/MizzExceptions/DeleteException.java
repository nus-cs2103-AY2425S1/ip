package MizzExceptions;

import util.Utility;

public class DeleteException extends MizzException {
  public DeleteException(String msg) {
    super(msg);
  }

  @Override
  public String toString() {
    return Utility.INDENT + "OOPs bad delete!: "
        + super.getMessage() + "\n"
        + Utility.INDENT + "Example usage: delete <valid_idx_from_1>";
  }
}
