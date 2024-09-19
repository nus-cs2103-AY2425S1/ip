package eli.exception;

/**
 * Represents an exception thrown when an unknown command is entered by the user.
 */
public class UnknownCommandException extends EliException{

  /**
   * Constructor for UnknownCommandException.
   */
  public UnknownCommandException() {
    super("I'm sorry, but I don't know what that means.");
  }
}
