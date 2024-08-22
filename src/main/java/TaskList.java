public class TaskList {
  private Task[] tasks = new Task[100];
  private int taskCount = 0;

  public TaskList() {
  }

  private String taskWord() {
    return this.taskCount == 1 ? "task" : "tasks";
  }

  public void addTask(Task task) {
    this.tasks[this.taskCount] = task;

    System.out.println("Got it. I've added this task:");
    System.out.println("  " + this.tasks[this.taskCount]);

    this.taskCount++;

    System.out.println("Now you have " + this.taskCount + " " + this.taskWord() + " in the list.");
  }

  public void displayTasks() throws EmptyListException {
    if (this.length() == 0) {
      throw new EmptyListException();
    }

    for (int i = 0; i < this.taskCount; i++) {
      Task task = this.tasks[i];
      System.out.println((i + 1) + ". " + task.toString());
    }
  }

  public void markTask(int taskNumber, boolean done)
      throws RedundantMarkException, RedundantUnmarkException, IndexOutOfBoundsException {
    if (taskNumber >= this.taskCount || taskNumber < 0) {
      throw new IndexOutOfBoundsException("Invalid task number. There are " + " " + this.taskWord() + " in your list.");
    }

    if (done) {
      boolean success = this.tasks[taskNumber].markAsDone();
      if (!success) {
        throw new RedundantMarkException(this.tasks[taskNumber]);
      }
      System.out.println("Nice! I've marked this task as done:");
    } else {
      boolean success = this.tasks[taskNumber].markAsUndone();
      if (!success) {
        throw new RedundantUnmarkException(this.tasks[taskNumber]);
      }
      System.out.println("OK, I've marked this task as not done yet:");
    }
    System.out.println("  " + this.tasks[taskNumber].toString());
  }

  public int length() {
    return this.taskCount;
  }
}
