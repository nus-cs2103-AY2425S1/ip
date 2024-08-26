public class TaskList {

  private Task[] tasks;
  private int taskCount;

  public TaskList() {
    this.tasks = new Task[100];
    this.taskCount = 0;
  }

  public void addTask(String task) {
    if (taskCount < 100) {
      tasks[taskCount] = new Task(task);
      taskCount++;
      System.out.println("____________________________________________________________");
      System.out.println(" added: " + task);
      System.out.println("____________________________________________________________");

    } else {
      System.out.println("Task list is full!!!");
    }
  }

  public void list() {
    System.out.println("____________________________________________________________");
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < taskCount; i++) {
      System.out.println((i + 1) + ". " + tasks[i]);
    }
    System.out.println("____________________________________________________________");
  }

  public void mark(int taskIdx) {
    tasks[taskIdx - 1].changeDoneStatus(true);
    System.out.println("____________________________________________________________");
    System.out.println(" Nice! I've marked this task as done:");
    System.out.println("   " + tasks[taskIdx - 1]);
    System.out.println("____________________________________________________________");

  }

  public void unmark(int taskIdx) {
    tasks[taskIdx - 1].changeDoneStatus(false);
    System.out.println("____________________________________________________________");
    System.out.println(" OK, I've marked this task as not done yet:");
    System.out.println("   " + tasks[taskIdx - 1]);
    System.out.println("____________________________________________________________");
  }
}
