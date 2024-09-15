package eli.task;

import java.util.ArrayList;
import java.util.List;

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
  public void addTask(Task task) {
    if (tasks.size() < 100) {
      tasks.add(task);
      System.out.println(" added: " + task);

    } else {
      System.out.println("Task list is full!!!");
    }
  }

  /**
   * Lists a task from the task list.
   */
  public void list() {
    System.out.println(" Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
  }

  public ArrayList getArrayList() {
    return tasks;
  }

  /**
   * Marks a task from the task list.
   *
   * @param taskIdx The index of the task to mark.
   */
  public void mark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(true);
    System.out.println(" Great job!");
    System.out.println("   " + tasks.get(taskIdx - 1));
  }

  /**
   * Unamrks a task from the task list.
   *
   * @param taskIdx The index of the task to unmark.
   */
  public void unmark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(false);
    System.out.println(" OK, I've marked this task as not done yet:");
    System.out.println("   " + tasks.get(taskIdx - 1));

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

  /**
   * Finds tasks that contain a specific keyword in their description.
   *
   * @param keyword The keyword to search for in task descriptions.
   * @return A list of tasks that contain the keyword.
   */
  public List<Task> findTasksByKeyword(String keyword) {
    List<Task> matchingTasks = new ArrayList<>();
    for (Task task : tasks) {
      if (task.getTask().contains(keyword)) {
        matchingTasks.add(task);
      }
    }
    return matchingTasks;
  }
}
