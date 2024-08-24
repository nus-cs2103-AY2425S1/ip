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

  public void mark() throws HamyoException {
    if (!marked) {
      marked = true;
      System.out.println("Yay! This task has been marked as completed.");
      System.out.println(this.toString());
      Hamyo.printLine();
    } else {
      throw new HamyoException("This task was already marked as completed!");
    }
  }

  public void unmark() throws HamyoException {
    if (this.marked) {
      marked = false;
      System.out.println("Oki! This task has been marked as incomplete.");
      System.out.println(this.toString());
      Hamyo.printLine();
    } else {
      throw new HamyoException("This task was already marked as incomplete!");
    }
  }

  @Override
  public String toString() {
    return (marked ? "[X]" : "[ ]") + " " + task;
  }
}
