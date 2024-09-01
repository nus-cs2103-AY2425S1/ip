package ahmad.exceptions.deadline;

import ahmad.exceptions.AhmadException;

/**
 * Exception class for empty deadline names.
 */
public class DeadlineEmptyNameException extends AhmadException {
  /**
   * Constructs a DeadlineEmptyNameException instance.
   */
  public DeadlineEmptyNameException() {
    super("Name should not be empty");
  }
}
