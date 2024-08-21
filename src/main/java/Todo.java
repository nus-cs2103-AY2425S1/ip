public class Todo extends Task {
    public Todo(String line) {
        super(line);
    }

  @Override
  public String toString() {
    return String.format("[T][%s] %s", completedStringRepresentation(), super.getName());
  }
}
