public class InvalidTaskDescriptionException extends Exception {
  public InvalidTaskDescriptionException(String type, String usage) {
    super("Invalid " + type + " format. Usage: " + usage);
  }
}
