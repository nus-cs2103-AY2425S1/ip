public class Task {

  private String task;
  private boolean isDone = false;

  public Task(String task) {
    this.task = task;
  }

  @Override
  public String toString() {
    String status = isDone ? "[X]" : "[ ]";
    return String.format("%s  %s", status, this.task);
  }

  public void printTask() {
    String status = isDone ? "[X]" : "[ ]";
    System.out.printf("%s  %s", status, this.task);
  }

  public void toggleDone() {
    this.isDone = !this.isDone;
  }
}
