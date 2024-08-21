package processor.task;

import exceptions.deadline.DeadlineEmptyNameException;
import exceptions.deadline.DeadlineInvalidArgsException;
import exceptions.event.EventEmptyNameException;
import exceptions.event.EventInvalidArgsException;
import exceptions.todo.TodoEmptyNameException;

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

  public static Task of(String type, String arg) throws DeadlineInvalidArgsException, DeadlineEmptyNameException, EventEmptyNameException, EventInvalidArgsException, TodoEmptyNameException {
    switch (type) {
      case "todo":
        if (arg.replaceAll("\\s+", "").isEmpty()) {
          throw new TodoEmptyNameException();
        }
        return new Todo(arg);
      case "deadline":
        final List<String> deadlineArgs = Arrays.asList(arg.split("/by "));
        if (deadlineArgs.get(0).replaceAll("\\s+", "").isEmpty()) {
          throw new DeadlineEmptyNameException();
        }
        if (deadlineArgs.size() != 2 || deadlineArgs.get(1).replaceAll("\\s+", "").isEmpty()) {
          throw new DeadlineInvalidArgsException();
        }

        return new Deadline(deadlineArgs.get(0), deadlineArgs.get(1));
      case "event":
        final List<String> eventArgs = Arrays.asList(arg.split("/"));
        if (eventArgs.get(0).replaceAll("\\s+", "").isEmpty()) {
          throw new EventEmptyNameException();
        }
        if (eventArgs.size() != 3) {
          throw new EventInvalidArgsException();
        }

        final String from, to;
        if (eventArgs.get(1).startsWith("to")) {
          if (!eventArgs.get(2).startsWith("from")
              || eventArgs.get(1).replaceAll("\\s+", "").length() == 2
              || eventArgs.get(2).replaceAll("\\s+", "").length() == 4) {
            throw new EventInvalidArgsException();
          }
          to = eventArgs.get(1).substring(3);
          from = eventArgs.get(2).substring(5);
        } else if (eventArgs.get(1).startsWith("from")) {
          if (!eventArgs.get(2).startsWith("to")
              || eventArgs.get(2).replaceAll("\\s+", "").length() == 2
              || eventArgs.get(1).replaceAll("\\s+", "").length() == 4) {
            throw new EventInvalidArgsException();
          }
          from = eventArgs.get(1).substring(5);
          to = eventArgs.get(2).substring(3);
        } else {
          throw new EventInvalidArgsException();
        }

        return new Event(eventArgs.get(0), from, to);
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
