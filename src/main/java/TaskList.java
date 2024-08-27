public class TaskList {

  private Task[] tasks;
  private int taskCount;

  public TaskList() {
    this.tasks = new Task[100];
    this.taskCount = 0;
  }

  public void addTask(String task) {
    // todo borrow book
    // deadline return book /by Sunday
    // event project meeting /from Mon 2pm /to 4pm

    String[] parts = task.split(" ", 2);
    String type = parts[0];
    String details = parts[1];

    Task newTask;
    if (type.equalsIgnoreCase("todo")) {
      newTask = new ToDo(details);
    } else if (type.equalsIgnoreCase("deadline")) {
      String[] deadlineParts = details.split(" /by ");
      newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
    } else if (type.equalsIgnoreCase("event")) {
      String[] eventParts = details.split(" /from | /to ");
      newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
    } else {
      newTask = new Task(task);
    }
    if (taskCount < 100) {
      tasks[taskCount] = newTask;
      taskCount++;
      System.out.println("____________________________________________________________");
      System.out.println(" added: " + newTask);
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
    System.out.println(" Great job!");
    System.out.println("   " + tasks[taskIdx - 1]);
    System.out.println("____________________________________________________________");

  }

  public void unmark(int taskIdx) {
    tasks[taskIdx - 1].changeDoneStatus(false);
    System.out.println("____________________________________________________________");
    System.out.println(" OK :(");
    System.out.println("   " + tasks[taskIdx - 1]);
    System.out.println("____________________________________________________________");
  }
}
