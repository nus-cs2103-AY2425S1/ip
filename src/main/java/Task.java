import java.util.regex.Pattern;

public class Task {
  private String description;
  private boolean isDone;
  private TaskType type;

  private final static String DELIMETER = "|";
  private final static String DELIMETER_SPACE = " " + DELIMETER + " ";
  private final static int EXPECTED_DELIMETED_PARAM_COUNT = 3;

  protected Task(String description, TaskType type) {
    this(description, type, false);
  }

  protected Task(String description, TaskType type, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
    this.type = type;
  }

  public String serialize() {
    return this.type.toChar() + DELIMETER_SPACE + (this.isDone ? "1" : "0") + DELIMETER_SPACE
        + this.description.replace(DELIMETER, "\\" + DELIMETER);
  }

  public static Task deserialize(String text) {
    String[] parts = text.split(Pattern.quote(DELIMETER_SPACE));
    if (parts.length != EXPECTED_DELIMETED_PARAM_COUNT) {
      throw new IllegalArgumentException("Invalid task format: " + text);
    }
    TaskType type = TaskType.fromChar(parts[0].charAt(0));
    boolean isDone = parts[1].equals("1");
    String description = parts[2].replace("\\" + DELIMETER, DELIMETER);
    return new Task(description, type, isDone);
  }

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
