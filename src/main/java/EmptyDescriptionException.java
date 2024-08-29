public class EmptyDescriptionException extends EliException{

  public EmptyDescriptionException(String taskType) {
    super("OOPS!!! The description of a " + taskType + " cannot be empty.");
  }
}
