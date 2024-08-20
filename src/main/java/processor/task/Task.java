package processor.task;

import java.util.Arrays;
import java.util.List;

public abstract class Task {
  private final String name;
  private final boolean state;

  protected Task(String name) {
    this.name = name;
    this.state = false;
  }

  protected Task(Task oldTask, boolean state) {
    this.name = oldTask.name;
    this.state = state;
  }

  public static Task of(String type, String arg) {
    switch (type) {
      case "todo":
        return new Todo(arg);
      case "deadline":
        final List<String> args = Arrays.asList(arg.split("/by "));
        return new Deadline(args.get(0), args.get(1));
    }

    return null;
  }

  public abstract Task mark();

  public abstract Task unmark();

  public abstract String getSymbol();

  public abstract String getExtraInformation();

  @Override
  public String toString() {
    return this.getSymbol() + "[" + (state ? "X" : " ") + "] " + name + this.getExtraInformation();
  }
}
