public class Task {

  private static int nTasks;
  private final int id;
  private final String task;
  private boolean marked;

  public Task(String task) {
    this.id = nTasks++;
    this.task = task;
  }

  public int getId() {
    return id;
  }

  public void mark() {
    if (!marked) {
      marked = true;
      System.out.println("Yay! This task has been marked as completed.");
      System.out.println(this.toString());
      Hamyo.printLine();
    } else {
      System.out.println("Oh no! This task was already marked as completed!");
      Hamyo.printLine();
    }
  }

  public void unmark() {
    if (this.marked) {
      marked = false;
      System.out.println("Oki! This task has been marked as incomplete.");
      System.out.println(this.toString());
      Hamyo.printLine();
    } else {
      System.out.println("Oh no! This task was already marked as incomplete!");
      Hamyo.printLine();
    }
  }

  @Override
  public String toString() {
    return (marked ? "[X]" : "[ ]") + " " + task;
  }
}
