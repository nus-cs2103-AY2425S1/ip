package eli.task;

import java.util.ArrayList;
import eli.exception.*;

/**
 * Contains the task list and provides methods to manipulate tasks within the list.
 */
public class TaskList {

  private ArrayList<Task>  tasks;

  /**
   * Constructor for TaskList.
   */
  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  /**
   * Constructor for TaskList with existing tasks.
   *
   * @param tasks The initial list of tasks.
   */
  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Returns a task list.
   */
  public ArrayList<Task> getTasks() {
    return tasks;
  }

  /**
   * Adds a task to the task list.
   *
   * @param task The task to add.
   */
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

  /**
   * Lists a task from the task list.
   */
  public void list() {
    System.out.println("____________________________________________________________");
    System.out.println(" Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
    System.out.println("____________________________________________________________");
  }

  /**
   * Marks a task from the task list.
   *
   * @param taskIdx The index of the task to mark.
   */
  public void mark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(true);
    System.out.println("____________________________________________________________");
    System.out.println(" Great job!");
    System.out.println("   " + tasks.get(taskIdx - 1));
    System.out.println("____________________________________________________________");
  }

  /**
   * Unamrks a task from the task list.
   *
   * @param taskIdx The index of the task to unmark.
   */
  public void unmark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(false);
    System.out.println("____________________________________________________________");
    System.out.println(" OK, I've marked this task as not done yet:");
    System.out.println("   " + tasks.get(taskIdx - 1));
    System.out.println("____________________________________________________________");

  }

  /**
   * Deletes a task from the task list.
   *
   * @param taskIdx The index of the task to delete.
   */
  public void delete(int taskIdx) {
    Task removedTask = tasks.remove(taskIdx - 1);
    System.out.println("____________________________________________________________");
    System.out.println(" OK. I've removed this task:");
    System.out.println("   " + removedTask);
    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("____________________________________________________________");
  }
}
