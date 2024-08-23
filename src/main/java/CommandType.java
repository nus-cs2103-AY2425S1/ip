public enum CommandType {
  BYE,
  LIST,
  MARK,
  UNMARK,
  DELETE,
  TODO,
  DEADLINE,
  EVENT;

  public static CommandType fromString(String command) {
    switch (command) {
      case "bye":
        return BYE;
      case "list":
        return LIST;
      case "mark":
        return MARK;
      case "unmark":
        return UNMARK;
      case "delete":
        return DELETE;
      case "todo":
        return TODO;
      case "deadline":
        return DEADLINE;
      case "event":
        return EVENT;
      default:
        throw new IllegalCommandException("Unknown command: " + command);
    }
  }

  public static String toString(CommandType commandType) {
    switch (commandType) {
      case BYE:
        return "bye";
      case LIST:
        return "list";
      case MARK:
        return "mark";
      case UNMARK:
        return "unmark";
      case DELETE:
        return "delete";
      case TODO:
        return "todo";
      case DEADLINE:
        return "deadline";
      case EVENT:
        return "event";
      default:
        throw new IllegalCommandException("Unknown command: " + commandType);
    }
  }
}
