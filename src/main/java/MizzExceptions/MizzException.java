package MizzExceptions;

import util.Utility;

public class MizzException extends Exception {
  public MizzException(String msg) {
    super(msg);
  }

  @Override
  public String toString() {
    return Utility.INDENT + "Oh no! an exception occured! :( " + super.getMessage();
  }
}