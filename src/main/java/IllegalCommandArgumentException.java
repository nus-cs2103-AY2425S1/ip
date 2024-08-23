public class IllegalCommandArgumentException extends IllegalArgumentException {
  private String command;
  private boolean hasDescription;
  private boolean isIntegerDescription;
  private CommandOption[] options;

  public IllegalCommandArgumentException(String command, boolean hasDescription, boolean isIntegerDescription,
      CommandOption[] options) {
    super();
    this.command = command;
    this.hasDescription = hasDescription;
    this.isIntegerDescription = isIntegerDescription;
    this.options = options;
  }

  @Override
  public String getMessage() {
    // Create a usage string for the command
    StringBuilder usage = new StringBuilder(command);
    if (hasDescription) {
      usage.append(isIntegerDescription ? " <value>" : " <description>");
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
