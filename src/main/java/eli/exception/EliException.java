package eli.exception;

/**
 * Represents a generic exception specific to the Eli application.
 */
public class EliException extends Exception {

  /**
   * Constructor for EliException.
   *
   * @param msg The error message associated with the exception.
   */
  public EliException(String msg) {
    super(msg);
  }
}
