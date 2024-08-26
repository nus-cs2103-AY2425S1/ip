package vuewee.parser;

import vuewee.command.CommandType;

public class IllegalCommandArgumentException extends IllegalCommandException {
  private CommandType command;
  private boolean hasDescription;
  private boolean isIntegerDescription;
  private CommandOption<?>[] options;

  public IllegalCommandArgumentException(CommandType command, boolean hasDescription, boolean isIntegerDescription,
      CommandOption<?>[] options) {
    super();
    this.command = command;
    this.hasDescription = hasDescription;
    this.isIntegerDescription = isIntegerDescription;
    this.options = options;
  }

  @Override
  public String getMessage() {
    // Create a usage string for the command
    StringBuilder usage = new StringBuilder(CommandType.toString(command));
    if (hasDescription) {
      usage.append(isIntegerDescription ? " <value>" : " <description>");
    }
    for (CommandOption<?> option : options) {
      usage.append(" /");
      usage.append(option.getOption());
      usage.append(" <");
      usage.append(option.getDescription());
      usage.append(">");
    }

    return "Invalid " + CommandType.toString(command) + " format. Usage: " + usage.toString();
  }
}
