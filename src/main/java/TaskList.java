import java.util.ArrayList;

public class TaskList {

  private ArrayList<Task>  tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  public ArrayList<Task> getTasks() {
    return tasks;
  }
  public void addTask(Task task) throws EmptyDescriptionException, UnknownCommandException{
    // todo borrow book
    // deadline return book /by Sunday
    // event project meeting /from Mon 2pm /to 4pm
    if (tasks.size() < 100) {
      tasks.add(task);
      System.out.println("____________________________________________________________");
      System.out.println(" added: " + task);
      System.out.println("____________________________________________________________");

    } else {
      System.out.println("Task list is full!!!");
    }
  }

  public void list() {
    System.out.println("____________________________________________________________");
    System.out.println(" Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
    System.out.println("____________________________________________________________");
  }


  public void mark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(true);
    System.out.println("____________________________________________________________");
    System.out.println(" Great job!");
    System.out.println("   " + tasks.get(taskIdx - 1));
    System.out.println("____________________________________________________________");
  }


  public void unmark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(false);
    System.out.println("____________________________________________________________");
    System.out.println(" OK, I've marked this task as not done yet:");
    System.out.println("   " + tasks.get(taskIdx - 1));
    System.out.println("____________________________________________________________");

  }

  public void delete(int taskIdx) {
    Task removedTask = tasks.remove(taskIdx - 1);
    System.out.println("____________________________________________________________");
    System.out.println(" OK. I've removed this task:");
    System.out.println("   " + removedTask);
    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("____________________________________________________________");
  }
}
