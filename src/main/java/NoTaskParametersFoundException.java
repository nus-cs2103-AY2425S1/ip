public class NoTaskParametersFoundException extends TaskListException {
  public NoTaskParametersFoundException(String type, String usage) {
    super("Invalid " + type + " format. Usage: " + usage);
  }
}
