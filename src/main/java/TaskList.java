import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks = new ArrayList<Task>();

  public TaskList() {
  }

  // Simple helper method to determine if the task count is 1 or more
  private String taskWord() {
    return this.tasks.size() == 1 ? "task" : "tasks";
  }

  // Add a task to the list
  public void addTask(Task task) {
    this.tasks.add(task);

    System.out.println("Got it. I've added this task:");
    System.out.println("  " + task.toString());
    System.out.println("Now you have " + this.tasks.size() + " " + this.taskWord() + " in the list.");
  }

  // Delete a task from the list at the specified index
  public void deleteTask(int taskNumber) throws IndexOutOfBoundsException {
    taskNumber--; // Adjust task number to match array index

    if (taskNumber >= this.tasks.size() || taskNumber < 0) {
      throw new IndexOutOfBoundsException(
          "Invalid task number. There are " + this.tasks.size() + " " + this.taskWord() + " in your list.");
    }

    Task task = this.tasks.get(taskNumber);
    System.out.println("Noted. I've removed this task:");
    System.out.println("  " + task.toString());
    System.out.println("Now you have " + (this.tasks.size() - 1) + " " + this.taskWord() + " in the list.");

    this.tasks.remove(taskNumber);
  }

  // Display all tasks in the list
  public void displayTasks() throws IllegalCommandException {
    if (this.length() == 0) {
      throw new IllegalCommandException("You have no tasks in your list.");
    }

    for (int i = 0; i < this.tasks.size(); i++) {
      Task task = this.tasks.get(i);
      System.out.println("  " + (i + 1) + ". " + task.toString());
    }
  }

  // Mark a task as done or not done
  public void markTask(int taskNumber, boolean done)
      throws IllegalCommandException {
    taskNumber--; // Adjust task number to match array index

    if (taskNumber >= this.tasks.size() || taskNumber < 0) {
      throw new IllegalCommandException(
          "Invalid task number. There are " + this.tasks.size() + " " + this.taskWord() + " in your list.");
    }

    Task task = this.tasks.get(taskNumber);
    if (done) {
      boolean success = task.markAsDone();
      if (!success) {
        throw new IllegalCommandException(task, true);
      }
      System.out.println("Nice! I've marked this task as done:");
    } else {
      boolean success = task.markAsUndone();
      if (!success) {
        throw new IllegalCommandException(task, false);
      }
      System.out.println("OK, I've marked this task as not done yet:");
    }
    System.out.println("  " + task.toString());
  }

  public int length() {
    return this.tasks.size();
  }
}
