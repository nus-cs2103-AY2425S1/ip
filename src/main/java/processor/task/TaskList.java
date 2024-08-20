package processor.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
  private static final List<Task> list = new ArrayList<Task>();

  public static List<String> getStringList() {
    if (list.isEmpty()) {
      return List.of("No items!");
    }
    return List.of(IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i)).reduce("", (acc, cur) -> acc + '\n' + cur));
  }

  public static String getSpecificTask(int idx) {
    return list.get(idx).toString();
  }

  public static void addTask(Task newTask) {
    list.add(newTask);
  }

  public static void mark(int idx) {
    list.set(idx, list.get(idx).mark());
  }

  public static void unmark(int idx) {
    list.set(idx, list.get(idx).unmark());
  }
}
