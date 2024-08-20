package processor.task;

public class Task {
  private final String name;
  private final boolean state;

  public Task(String name) {
    this.name = name;
    this.state = false;
  }

  private Task(String name, boolean state) {
    this.name = name;
    this.state = state;
  }

  public Task mark() {
    return new Task(this.name, true);
  }

  public Task unmark() {
    return new Task(this.name, false);
  }

  @Override
  public String toString() {
    return "[" + (state ? "X" : " ") + "] " + name;
  }
}
