package processor.task;

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
    return new Todo(arg);
  }

  public abstract Task mark();

  public abstract Task unmark();

  public abstract String getSymbol();

  @Override
  public String toString() {
    return this.getSymbol() + "[" + (state ? "X" : " ") + "] " + name;
  }
}
