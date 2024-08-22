import java.util.ArrayList;

public class TaskStorage {

  private final ArrayList<Task> tasks;

  public TaskStorage() {
    tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    int index = 1;
    for (Task task : tasks) {
      stringBuilder.append(index).append(". ").append(task.toString()).append("\n");
      index++;
    }
    return stringBuilder.toString();
  }

}
