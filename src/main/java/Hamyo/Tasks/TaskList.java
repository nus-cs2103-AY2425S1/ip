package Hamyo.Tasks;

import Hamyo.Misc.HamyoException;
import Hamyo.Misc.UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the list of users' tasks. Provides operations to add/delete etc.
 *
 * @author Han Yu
 */
public class TaskList extends ArrayList<Task> {

  /**
   * Constructor for TaskList. Creates a new instance of an ArrayList.
   */
  public TaskList() {
    super();
  }

  /**
   * Adds the specified Task to the list of users' tasks.
   *
   * @param taskType Type of task (i.e. To-Do, Deadline, Event).
   * @param task Trailing String after to-do/deadline/event command,
   *             (e.g. " apple", " banana /from 2002-09-18).
   * @throws HamyoException If the command is incomplete/invalid.
   */
  public void addTask (TaskType taskType, String task) throws HamyoException {
    if (taskType.equals(TaskType.TODO)) {
      if (task.length() <= 1) {
        throw new HamyoException("Usage: todo [task description]");
      }
      this.add(new ToDo(new String[]{task.substring(1)}));
    } else if (taskType.equals(TaskType.DEADLINE)) {
      if (task.length() <= 1) {
        throw new HamyoException("Usage: deadline [task description] /by [deadline]");
      }
      String[] split = task.substring(1).split(" /by ");
      if (split.length != 2) {
        throw new HamyoException("Usage: deadline [task description] /by [deadline]");
      }
      this.add(new Deadline(split));
    } else if (taskType.equals(TaskType.EVENT)) {
      if (task.length() <= 1) {
        throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
      }
      String[] split = task.substring(1).split(" /from | /to ");
      if (split.length != 3) {
        throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
      }
      this.add(new Event(split));
    }

    UI.printAddTask(this.get(this.size() - 1), this.size());
  }

  /**
   * Deletes the specified Task from the list of users' tasks.
   *
   * @param str Trailing String after delete command, (e.g. " 1", " 2", " 3").
   * @throws HamyoException If the command is incomplete/invalid.
   */
  public void deleteTask(String str) throws HamyoException {
    try {
      if (str.length() <= 1) {
        throw new HamyoException("Usage: delete [index]");
      }
      int index = Integer.parseInt(str.substring(1)) - 1;
      if (index < 0 || index >= this.size()) {
        throw new HamyoException("Usage: delete [index]");
      }
      Task deletedTask = this.remove(index);

      UI.printDeleteTask(deletedTask, this.size());
    } catch (NumberFormatException e) {
      throw new HamyoException("Usage: delete [index]");
    }
  }

  /**
   * List all the users' tasks onto the terminal.
   */
  public void listTasks() {
    StringBuilder tasksList = new StringBuilder();
    for (int i = 1; i < this.size() + 1; i++) {
      if (!tasksList.isEmpty()) {
        tasksList.append("\n");
      }
      tasksList.append(i).append(". ").append(this.get(i - 1).toString());
    }

    UI.printListTasks(tasksList.toString());
  }

  /**
   * List all the users' tasks on the specified date onto the terminal.
   *
   * @param str Trailing String after listDate command, (e.g. " 2002-09-18").
   * @throws HamyoException If the command is incomplete/invalid.
   */
  public void listTasksByDate(String str) throws HamyoException {
    try {
      LocalDate date = LocalDate.parse(str.substring(1));
      StringBuilder tasksList = new StringBuilder();
      int counter = 1;
      for (int i = 0; i < this.size(); i++) {
        if (this.get(i) instanceof Deadline || this.get(i) instanceof Event) {
          if (this.get(i).occursToday(date)) {
            if (!tasksList.isEmpty()) {
              tasksList.append("\n");
            }
            tasksList.append(counter++).append(". ").append(this.get(i).toString());
          }
        }
      }

      UI.printListTasksByDate(tasksList.toString(), date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    } catch (Exception e) {
      throw new HamyoException("Usage: listDate yyyy-MM-dd.");
    }
  }

  /**
   * Mark a task in the list of users' tasks.
   *
   * @param str Trailing String after mark command, (e.g. " 1", " 2", " 3").
   * @throws HamyoException If the command is incomplete/invalid.
   */
  public void markTask(String str) throws HamyoException {
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

  /**
   * Unmark a task in the list of users' tasks.
   *
   * @param str Trailing String after mark command, (e.g. " 1", " 2", " 3").
   * @throws HamyoException If the command is incomplete/invalid.
   */
  public void unmarkTask(String str) throws HamyoException {
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
}
