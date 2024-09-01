package ahmad.processor.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskList {
  private static final List<Task> list = new ArrayList<Task>();

  private static List<String> getStringList(List<Task> list) {
    if (list.isEmpty()) {
      return List.of("No items!");
    }
    return List.of(IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i)).reduce("", (acc, cur) -> acc + '\n' + cur));
  }

  public static List<String> getStringList() {
    return getStringList(TaskList.list);
  }

  public static String getSpecificTask(int idx) {
    return list.get(idx).toString();
  }

  public static int getTaskCount() {
    return list.size();
  }

  public static void addTask(Task newTask) {
    list.add(newTask);
  }

  public static void deleteTask(int idx) {
    list.remove(idx);
  }

  public static void mark(int idx) {
    list.set(idx, list.get(idx).mark());
  }

  public static void unmark(int idx) {
    list.set(idx, list.get(idx).unmark());
  }

  public static List<String> findTask(String keyword) {
      Stream<Task> stream = list.stream().filter((Task task) -> task.toString().contains(keyword));
      return getStringList(stream.toList());
  }
}
