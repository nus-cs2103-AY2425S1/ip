
public class TaskList {

  private String[] tasks;
  private int taskCount;

  public TaskList() {
    this.tasks = new String[100];
    this.taskCount = 0;
  }

  public void addTask(String task) {
    if (taskCount < 100) {
      tasks[taskCount] = task;
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
    for (int i = 0; i < taskCount; i++) {
      System.out.println((i + 1) + ". " + tasks[i]);
    }
    System.out.println("____________________________________________________________");
  }
}
