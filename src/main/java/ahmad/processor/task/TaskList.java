package ahmad.processor.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class to store list of tasks.
 */
public class TaskList {
  private static final List<Task> list = new ArrayList<Task>();

  /**
   * Returns the list of tasks in a single string.
   *
   * @return A list of single element, which is the required string.
   */
  public static List<String> getStringList() {
    if (list.isEmpty()) {
      return List.of("No items!");
    }
    return List.of(IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i)).reduce("", (acc, cur) -> acc + '\n' + cur));
  }

  /**
   * Returns string of a specific task index.
   *
   * @param idx Index of the task in question.
   * @return String of task at index.
   */
  public static String getSpecificTask(int idx) {
    return list.get(idx).toString();
  }

  /**
   * Returns number of tasks.
   *
   * @return Number of tasks.
   */
  public static int getTaskCount() {
    return list.size();
  }

  /**
   * Adds new task to list.
   *
   * @param newTask The new task in question.
   */
  public static void addTask(Task newTask) {
    list.add(newTask);
  }

  /**
   * Deletes task from list.
   *
   * @param idx Index of task to be deleted.
   */
  public static void deleteTask(int idx) {
    list.remove(idx);
  }

  /**
   * Marks a task in the list as done.
   *
    * @param idx Index of task to be marked.
   */
  public static void mark(int idx) {
    list.set(idx, list.get(idx).mark());
  }

  /**
   * Marks a task in the list as not done.
   *
   * @param idx Index of task to be unmarked.
   */
  public static void unmark(int idx) {
    list.set(idx, list.get(idx).unmark());
  }
}
