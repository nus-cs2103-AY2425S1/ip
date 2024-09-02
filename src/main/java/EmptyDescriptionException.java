/**
 * Represents an exception thrown when a task description is empty.
 */
public class EmptyDescriptionException extends EliException{

  /**
   * Constructor for EmptyDescriptionException.
   *
   * @param taskType The type of task that has an empty description.
   */
  public EmptyDescriptionException(String taskType) {
    super("OOPS!!! The description of a " + taskType + " cannot be empty.");
  }
}
