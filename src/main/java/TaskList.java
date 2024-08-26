import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks = new ArrayList<Task>();

  public TaskList() {
  }

  public int size() {
    return this.tasks.size();
  }

  // Add a task to the list
  public void add(Task task) {
    this.tasks.add(task);
  }

  // Get a task from the list at the specified index (0-based)
  public Task get(int index) throws IndexOutOfBoundsException {
    return this.tasks.get(index);
  }

  // Delete a task from the list at the specified index (0-based)
  public void remove(int taskNumber) throws IndexOutOfBoundsException {
    this.tasks.remove(taskNumber);
  }

  // Mark a task as done or not done
  public boolean markTask(int taskNumber, boolean done)
      throws IndexOutOfBoundsException {
    if (taskNumber >= this.tasks.size() || taskNumber < 0) {
      throw new IndexOutOfBoundsException();
    }

    Task task = this.tasks.get(taskNumber);
    if (done) {
      boolean success = task.markAsDone();
      return success;
    } else {
      boolean success = task.markAsUndone();
      return success;
    }
  }
}
