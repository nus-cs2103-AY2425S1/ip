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
    assert tasks != null : "Task list must not be null";
    this.tasks = tasks;
  }

  /**
   * Returns a task list.
   */
  public ArrayList<Task> getTasks() {
    return tasks;
  }

  /**
   * Returns a length of a task list.
   */
  public int getTaskListLength() {
    return tasks.size();
  }

  /**
   * Adds a task to the task list.
   *
   * @param task The task to add.
   */
  public void addTask(Task task) {
    assert task != null : "Task to be added cannot be null";
    assert tasks.size() < 100 : "Task list is already full!!!";

    if (tasks == null) {
      throw new IllegalArgumentException("Task to be added cannot be null");
    }
    if (tasks.size() > 100) {
      throw new IllegalArgumentException("Task list is already full!!!");
    }
    tasks.add(task);
    System.out.println(" added: " + task);
  }

  /**
   * Lists a task from the task list.
   */
  public void list() {
    assert tasks != null : "Task list is empty";
    assert tasks.size() > 0 : "Task list is empty";

    if (tasks.size() < 0) {
      throw new IllegalArgumentException("Task list is empty");
    }
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
    assert taskIdx > 0 && taskIdx <= tasks.size() : "Invalid task index";

    if (taskIdx <= 0 || taskIdx > tasks.size()) {
      throw new IllegalArgumentException("Invalid task index: " + taskIdx);
    }
    tasks.get(taskIdx - 1).changeDoneStatus(true);
  }

  /**
   * Unamrks a task from the task list.
   *
   * @param taskIdx The index of the task to unmark.
   */
  public void unmark(int taskIdx) {
    assert taskIdx > 0 && taskIdx <= tasks.size() : "Invalid task index";

    if (taskIdx <= 0 || taskIdx > tasks.size()) {
      throw new IllegalArgumentException("Invalid task index: " + taskIdx);
    }
    tasks.get(taskIdx - 1).changeDoneStatus(false);
  }

  /**
   * Deletes a task from the task list.
   *
   * @param taskIdx The index of the task to delete.
   */
  public void delete(int taskIdx) {
    assert taskIdx > 0 && taskIdx <= tasks.size() : "Invalid task index";
    if (taskIdx <= 0 || taskIdx > tasks.size()) {
      throw new IllegalArgumentException("Invalid task index: " + taskIdx);
    }
    tasks.remove(taskIdx - 1);
  }

  /**
   * Finds tasks that contain a specific keyword in their description.
   *
   * @param keyword The keyword to search for in task descriptions.
   * @return A list of tasks that contain the keyword.
   */
  public List<Task> findTasksByKeyword(String keyword) {
    assert keyword != null && !keyword.trim().isEmpty() : "Keyword cannot be null or empty";

    List<Task> matchingTasks = new ArrayList<>();
    for (Task task : tasks) {
      if (task.getTask().contains(keyword)) {
        matchingTasks.add(task);
      }
    }
    return matchingTasks;
  }
}
