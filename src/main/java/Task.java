public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String toString() {
    String indicator = this.isDone ? "[X]" : "[ ]";
    return indicator + " " + this.description;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void unmarkAsDone() {
    this.isDone = false;
  }

}
