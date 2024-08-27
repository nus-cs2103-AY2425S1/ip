package vuewee.task;

import java.util.regex.Pattern;

public class EventTask extends Task {
  private TaskLocalDate from;
  private TaskLocalDate to;

  private static final int EXPECTED_DELIMETED_PARAM_COUNT = 4;

  public EventTask() {
    super();
    this.type = TaskType.EVENT;
  }

  public EventTask(String description, TaskLocalDate from, TaskLocalDate to) {
    super(description, TaskType.EVENT);
    this.from = from;
    this.to = to;
  }

  @Override
  String serialize() {
    return this.type.toChar() + Task.DELIMETER_SPACE + (this.isDone ? "1" : "0") + Task.DELIMETER_SPACE
        + this.description.replace(Task.DELIMETER, "\\" + Task.DELIMETER) + Task.DELIMETER_SPACE + this.from.serialize()
        + Task.DELIMETER_SPACE + this.to.serialize();
  }

  @Override
  void deserialize(String serializedTask) {
    String[] parts = serializedTask.split(Pattern.quote(DELIMETER_SPACE), EXPECTED_DELIMETED_PARAM_COUNT);

    if (parts.length != EXPECTED_DELIMETED_PARAM_COUNT) {
      throw new IllegalArgumentException("Invalid task format: " + serializedTask);
    }

    boolean isDone = parts[0].equals("1");
    String description = parts[1].replace("\\" + DELIMETER, DELIMETER);

    this.description = description;
    this.isDone = isDone;
    this.from = TaskLocalDate.deserialize(parts[2]);
    this.to = TaskLocalDate.deserialize(parts[3]);
  }

  @Override
  public String toString() {
    return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
  }
}