package ahmad.exceptions;

/**
 * Exception class for invalid commands.
 */
public class CommandInvalidException extends AhmadException {
  /**
   * Constructs a CommandInvalidException instance.
   */
  public CommandInvalidException() {
    super("Hmmm I don't really understand that...");
  }
}
