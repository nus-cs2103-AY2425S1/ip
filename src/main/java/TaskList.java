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

  public void displayTasks() {
    if (this.length() == 0) {
      System.err.println("You have no tasks in your list.");
      return;
    }

    for (int i = 0; i < this.taskCount; i++) {
      Task task = this.tasks[i];
      System.out.println((i + 1) + ". " + task.toString());
    }
  }

  public void markTask(int taskNumber, boolean done) {
    if (taskNumber >= this.taskCount || taskNumber < 0) {
      System.out.println("Invalid task number. There are " + " " + this.taskWord() + " in your list.");
      return;
    }

    if (done) {
      boolean success = this.tasks[taskNumber].markAsDone();
      System.out.println(success ? "Nice! I've marked this task as done:" : "This task is already done:");
    } else {
      boolean success = this.tasks[taskNumber].markAsUndone();
      System.out.println(
          success ? "OK, I've marked this task as not done yet:" : "This task is already marked as not done:");
    }

    System.out.println("  " + this.tasks[taskNumber].toString());
  }

  public int length() {
    return this.taskCount;
  }
}
