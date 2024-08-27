package vuewee.task;

public abstract class Task {
  protected String description;
  protected boolean isDone;
  protected TaskType type;

  final static String DELIMETER = "|";
  final static String DELIMETER_SPACE = " " + DELIMETER + " ";

  protected Task() {
  };

  protected Task(String description, TaskType type) {
    this(description, type, false);
  }

  protected Task(String description, TaskType type, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
    this.type = type;
  }

  abstract void deserialize(String serializedTask);

  abstract String serialize();

  private String getStatusIcon() {
    return (this.isDone ? "X" : " "); // mark done task with X
  }

  public boolean markAsDone() {
    boolean success = !this.isDone;
    this.isDone = true;
    return success;
  }

  public boolean markAsUndone() {
    boolean success = this.isDone;
    this.isDone = false;
    return success;
  }

  @Override
  public String toString() {
    return "[" + this.type.toChar() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
  }
}
