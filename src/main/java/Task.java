public class Task {
  private boolean completed;
  private String name;

  public String getName() {
    return name;
  }

  public Task(String name) {
    this.completed = false;
    this.name = name;
  }

  public void mark() {
    this.completed = true;
  }

  public void unMark() {
    this.completed = false;
  }

  String completedStringRepresentation() {
    if (!completed) {
	    return " ";
    } else {
	    return "X";
    }
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", completedStringRepresentation(), this.name);
  }
}
