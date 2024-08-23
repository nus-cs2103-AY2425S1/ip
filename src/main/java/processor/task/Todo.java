package processor.task;

public class Todo extends Task {
  public Todo(String name) {
    super(name);
  }

  private Todo(Todo a, boolean state) {
    super(a, state);
  }

  @Override
  public Todo mark() {
    return new Todo(this, true);
  }

  @Override
  public Todo unmark() {
    return new Todo(this, false);
  }

  @Override
  public String getExtraInformation() {
    return "";
  }

  @Override
  public String getSymbol() {
    return "[T]";
  }
}
