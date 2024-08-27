import java.time.LocalDate;

public class Task {

  private static int nTasks;
  private final int id;
  private final String task;
  private boolean marked;

  public Task(String[] task) {
    this.id = nTasks++;
    this.task = task[0];
  }

  public int getId() {
    return id;
  }

  public boolean isMarked() {
    return marked;
  }

  public String getTask() {
    return task;
  }

  public void mark() throws HamyoException {
    if (!marked) {
      marked = true;
      System.out.println("Yay! This task has been marked as completed.");
      System.out.println(this.toString());
      UI.printLine();
    } else {
      throw new HamyoException("This task was already marked as completed!");
    }
  }

  public void unmark() throws HamyoException {
    if (this.marked) {
      marked = false;
      System.out.println("Oki! This task has been marked as incomplete.");
      System.out.println(this.toString());
      UI.printLine();
    } else {
      throw new HamyoException("This task was already marked as incomplete!");
    }
  }

  @Override
  public String toString() {
    return (marked ? "[X]" : "[ ]") + " " + task;
  }

  public String toFileFormat() {
    return (this.marked ? "1" : "0") + " | " + this.task;
  }

  public boolean occursToday(LocalDate date) {
    return true;
  }
}
