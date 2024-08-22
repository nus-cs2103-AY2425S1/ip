package util;

import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskHist {
  private List<Task> tasks;

  public TaskHist() {
    this.tasks = new ArrayList<>(100);
  }

  /**
   * Method to add into the running list of tasks entered.
   * 
   * @param taskType    Can be one of todo | deadline | event
   * @param taskDetails The info for the task
   * @return
   */
  public boolean addTask(String taskType, String[] taskDetails) {
    Task newTask;
    switch (taskType) {
      case "todo":
        newTask = new ToDo(taskDetails[1]);
        break;
      case "deadline":
        newTask = new Deadline(taskDetails[1], taskDetails[2]);
        break;
      case "event":
        newTask = new Event(taskDetails[1], taskDetails[2], taskDetails[3]);
        break;
      default:
        newTask = new Task("Hm whats this?");
        break;
    }
    // create the message to be printed
    StringBuilder sb = new StringBuilder(String.format(
        "%sGot it I've added this task:\n", Utility.INDENT));
    sb.append(String.format("%s%s%s\n", Utility.INDENT, Utility.INDENT, newTask.toString()));
    sb.append(String.format(
        "%sNow you have %d tasks in your list.", Utility.INDENT, this.tasks.size() + 1));
    Utility.prettyPrint(sb.toString());
    return this.tasks.add(newTask);
  }

  /**
   * Prints the items in the hist.
   */
  public void prettyPrintAll() {
    int no = 1;
    System.out.println(Utility.INDENTED_LINE);
    System.out.println(String.format("%sHere are tasks in your list:", Utility.INDENT));
    for (Task task : this.tasks) {
      System.out.println(String.format("%s%d. %s", Utility.INDENT, no++, task));
    }
    System.out.println(Utility.INDENTED_LINE);
  }

  /**
   * Mark the selected idx as done.
   * 
   * @param idx Idx to be edited. Starting from 1.
   */
  public void markAsDone(int idx) {
    Task entry = this.tasks.get(--idx);
    StringBuilder sb = new StringBuilder(Utility.INDENT);
    if (!entry.isDone()) {
      entry.markDone();
      sb.append("Nice! I've marked this task as done:\n");
    } else {
      sb.append("Task has already been completed!\n");
    }
    sb.append(String.format("%s%s%s", Utility.INDENT, Utility.INDENT, entry.toString()));
    Utility.prettyPrint(sb.toString());
  }

  /**
   * Mark the selected idx as undone.
   * 
   * @param idx Idx to be edited. Starting from 1.
   */
  public void markAsUndone(int idx) {
    Task entry = this.tasks.get(--idx);
    StringBuilder sb = new StringBuilder();
    if (entry.isDone()) {
      entry.markUndone();
      sb.append(String.format("%sOK, I've marked this task as not done yet:\n", Utility.INDENT));
    } else {
      sb.append(String.format("%sTask is already unmarked!\n", Utility.INDENT));
    }
    sb.append(String.format("%s%s%s", Utility.INDENT, Utility.INDENT, entry.toString()));
    Utility.prettyPrint(sb.toString());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int itemNo = 1;

    for (Task task : this.tasks) {
      sb.append(String.format("%d. %s\n", itemNo++, task));
    }
    return sb.toString();
  }

  /**
   * Utility method to check if idx is valid.
   * 
   * @param idx The idx to be checked.
   * @return true if is within range else false.
   */
  public boolean isValidIdx(int idx) {
    idx--;
    return idx < this.tasks.size() && idx >= 0;
  }
}
