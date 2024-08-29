import java.util.ArrayList;

public class TaskList {

  private ArrayList<Task>  tasks;
  //private int taskCount;

  public TaskList() {
    this.tasks = new ArrayList<>();
    //this.taskCount = 0;
  }

  public void addTask(String task) throws EmptyDescriptionException, UnknownCommandException{
    // todo borrow book
    // deadline return book /by Sunday
    // event project meeting /from Mon 2pm /to 4pm

    String[] parts = task.split(" ", 2);
    String type = parts[0];
    String details = parts[1];

    if (details.isEmpty()) {
      throw new EmptyDescriptionException(type);
    }

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
      // newTask = new Task(task);
      throw new UnknownCommandException();
    }
    if (tasks.size() < 100) {
      tasks.add(newTask);
      System.out.println("____________________________________________________________");
      System.out.println(" added: " + newTask);
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
