public class InvalidTaskDescriptionException extends TaskListException {
  public InvalidTaskDescriptionException(String type, String usage) {
    super("Invalid " + type + " format. Usage: " + usage);
  }
}
