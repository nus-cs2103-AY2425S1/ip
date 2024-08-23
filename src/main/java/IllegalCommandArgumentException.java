public class IllegalCommandArgumentException extends IllegalArgumentException {
  private String command;
  private boolean hasDescription;
  private CommandOption[] options;

  public IllegalCommandArgumentException(String command, boolean hasDescription, CommandOption[] options) {
    super();
    this.command = command;
    this.hasDescription = hasDescription;
    this.options = options;
  }

  @Override
  public String getMessage() {
    // Create a usage string for the command
    StringBuilder usage = new StringBuilder(command);
    if (hasDescription) {
      usage.append(" <description>");
    }
    for (CommandOption option : options) {
      usage.append(" /");
      usage.append(option.getOption());
      usage.append(" <");
      usage.append(option.getDescription());
      usage.append(">");
    }

    return "Invalid " + command + " format. Usage: " + usage.toString();
  }
}
