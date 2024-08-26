public enum CommandType {
  BYE(ByeCommand.class),
  LIST(ListCommand.class),
  MARK(MarkCommand.class),
  UNMARK(UnmarkCommand.class),
  DELETE(DeleteCommand.class),
  TODO(TodoCommand.class),
  DEADLINE(DeadlineCommand.class),
  EVENT(EventCommand.class);

  private final Class<? extends Command> commandClass;

  CommandType(Class<? extends Command> commandClass) {
    this.commandClass = commandClass;
  }

  public Command createCommand() {
    try {
      return commandClass.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Failed to create command instance", e);
    }
  }

  public static CommandType fromString(String command) {
    try {
      return CommandType.valueOf(command.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalCommandException("Unknown command: " + command);
    }
  }

  public static String toString(CommandType commandType) {
    return commandType.name().toLowerCase();
  }
}