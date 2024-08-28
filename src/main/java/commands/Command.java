package commands;

import MizzExceptions.MizzException;
import util.Storage;
import util.TaskList;
import util.Ui;

public abstract class Command {
  protected CommandTypes command;

  public static enum CommandTypes {
    TODO,
    DEADLINE,
    EVENT;

    public static CommandTypes toCommandType(String type) throws MizzException {
      switch (type.toLowerCase()) {
        case "todo":
          return TODO;
        case "deadline":
          return DEADLINE;
        case "event":
          return EVENT;
        default:
          throw new MizzException("Unknown command type: " + type);
      }
    }
  }

  abstract void execute(TaskList tl, Ui ui, Storage storage, String... details) throws MizzException;

  @Override
  public String toString() {
    return "Command";
  }
}
