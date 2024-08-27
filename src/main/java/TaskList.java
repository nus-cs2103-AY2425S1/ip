import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TaskList - contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList extends ArrayList<Task> {

  public TaskList() {
    super();
  }

  public void addTask (Hamyo hamyo, Hamyo.TaskType taskType, String task) throws HamyoException {
    if (taskType.equals(Hamyo.TaskType.TODO)) {
      if (task.length() <= 1) {
        throw new HamyoException("Usage: todo [task description]");
      }
      this.add(new ToDo(new String[]{task.substring(1)}));
    } else if (taskType.equals(Hamyo.TaskType.DEADLINE)) {
      if (task.length() <= 1) {
        throw new HamyoException("Usage: deadline [task description] /by [deadline]");
      }
      String[] split = task.substring(1).split(" /by ");
      if (split.length != 2) {
        throw new HamyoException("Usage: deadline [task description] /by [deadline]");
      }
      this.add(new Deadline(split));
    } else if (taskType.equals(Hamyo.TaskType.EVENT)) {
      if (task.length() <= 1) {
        throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
      }
      String[] split = task.substring(1).split(" /from | /to ");
      if (split.length != 3) {
        throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
      }
      this.add(new Event(split));
    }
    System.out.println("Got it. I've added this task:");
    System.out.println(this.get(this.size() - 1).toString());
    System.out.printf("There are %d tasks in the list now.\n", this.size());
    UI.printLine();
  }

  public void listTasks(Hamyo hamyo) throws HamyoException {
    System.out.println("These are your tasks:");
    for (int i = 0; i < this.size(); i++) {
      System.out.println((i + 1) + ". " + this.get(i).toString());
    }
    UI.printLine();
  }
  public void listTasksByDate(Hamyo hamyo, String str) throws HamyoException {
    try {
      LocalDate date = LocalDate.parse(str.substring(1));
      System.out.println("These are your tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ".");
      int counter = 1;
      for (int i = 0; i < this.size(); i++) {
        if (this.get(i) instanceof Deadline || this.get(i) instanceof Event) {
          if (this.get(i).occursToday(date)) {
            System.out.println(counter++ + ". " + this.get(i).toString());
          }
        }
      }
      UI.printLine();
    } catch (Exception e) {
      throw new HamyoException("Usage: listDate yyyy-MM-dd.");
    }
  }

  public void markTask(Hamyo hamyo, String str) throws HamyoException {
    try {
      if (str.length() <= 1) {
        throw new HamyoException("Usage: mark [index]");
      }
      int index = Integer.parseInt(str.substring(1)) - 1;
      if (index < 0 || index >= this.size()) {
        throw new HamyoException("Usage: mark [index]");
      }
      this.get(index).mark(true);
    } catch (NumberFormatException e) {
      throw new HamyoException("Usage: mark [index]");
    }
  }

  public void unmarkTask(Hamyo hamyo, String str) throws HamyoException {
    try {
      if (str.length() <= 1) {
        throw new HamyoException("Usage: unmark [index]");
      }
      int index = Integer.parseInt(str.substring(1)) - 1;
      if (index < 0 || index >= this.size()) {
        throw new HamyoException("Usage: unmark [index]");
      }
      this.get(index).unmark(true);
    } catch (NumberFormatException e) {
      throw new HamyoException("Usage: unmark [index]");
    }
  }

  public void deleteTask(Hamyo hamyo, String str) throws HamyoException {
    try {
      if (str.length() <= 1) {
        throw new HamyoException("Usage: delete [index]");
      }
      int index = Integer.parseInt(str.substring(1)) - 1;
      if (index < 0 || index >= this.size()) {
        throw new HamyoException("Usage: delete [index]");
      }
      System.out.println("Noted. I've removed this task:");
      System.out.println(this.get(index).toString());
      this.remove(index);
      System.out.printf("There are %d tasks in the list now.\n", this.size());
      UI.printLine();
    } catch (NumberFormatException e) {
      throw new HamyoException("Usage: delete [index]");
    }
  }
}
