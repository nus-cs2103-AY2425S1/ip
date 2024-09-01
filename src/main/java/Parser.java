public class Parser {
  public static Command parse(String fullCommand) throws EliException {
    String[] parts = fullCommand.split(" ", 2);
    String commandWord = parts[0];
    String description = parts.length > 1 ? parts[1].trim() : "";

    switch (commandWord.toLowerCase()) {
      case "list":
        return new ListCommand();
      case "mark":
        return new MarkCommand(Integer.parseInt(description));
      case "unmark":
        return new UnmarkCommand(Integer.parseInt(description));
      case "delete":
        return new DeleteCommand(Integer.parseInt(description));
      case "todo":
        if (description.isEmpty()) {
          throw new EliException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(description));
      case "deadline":
        String[] deadlineParts = description.split(" /by ");
        if (deadlineParts.length < 2) {
          throw new EliException("The deadline must be in the format 'description /by yyyy-MM-dd'.");
        }
        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
      case "event":
        String[] eventParts = description.split(" /from ");
        if (eventParts.length < 2) {
          throw new EliException("The event must be in the format 'description /from yyyy-MM-dd /to yyyy-MM-dd'.");
        }
        String[] times = eventParts[1].split(" /to ");
        return new AddCommand(new Event(eventParts[0], times[0], times[1]));
      case "bye":
        return new ExitCommand();
      default:
        throw new UnknownCommandException();
    }
  }

  public static Task parseTaskFromFile(String line) {
    String[] parts = line.split(" \\| ");
    String type = parts[0];
    boolean isDone = parts[1].equals("1");
    String description = parts[2];

    Task task = null;
    switch (type) {
      case "T":
        task = new ToDo(description);
        break;
      case "D":
        task = new Deadline(description, parts[3]);
        break;
      case "E":
        String[] eventDetails = parts[3].split(" to ");
        task = new Event(description, eventDetails[0], eventDetails[1]);
        break;
    }

    if (task != null && isDone) {
      task.changeDoneStatus(true);
    }

    return task;
  }
}
