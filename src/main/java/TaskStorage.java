import java.util.ArrayList;

public class TaskStorage {

  private final ArrayList<Task> tasks;

  public TaskStorage() {
    tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public Task getTask(int taskIdx) {
    return tasks.get(taskIdx);
  }

  public int getSize() {
    return tasks.size();
  }

  public void setTaskAsDone(int taskIdx) {
    tasks.get(taskIdx).setDone(true);
  }

  public void setTaskAsNotDone(int taskIdx) {
    tasks.get(taskIdx).setDone(false);
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
