package util;

import java.util.List;

import tasks.Task;
import tasks.ToDos;

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
   * @param taskDetails The info in the task
   * @return
   */
  public boolean addTask(String taskType, String taskDetails) {
    Task newTask;
    switch (taskType) {
      case "todo":
        newTask = new ToDos(taskDetails);
        break;
      default:
        newTask = new Task("Hm whats this?");
        break;
    }
    // create the message to be printed
    StringBuilder sb = new StringBuilder(String.format(
        "%sGot it I've added this task:\n", Utility.INDENT));
    sb.append(String.format("%s%s\n", Utility.INDENT, newTask.toString()));
    sb.append(String.format(
        "%sNow you have %d tasks in your list.", Utility.INDENT, this.tasks.size() + 1));
    TaskHist.prettyPrint(sb.toString());
    return this.tasks.add(newTask);
  }

  /**
   * Prints the items in the hist
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
    sb.append(String.format("%s%s", Utility.INDENT, entry.toString()));
    TaskHist.prettyPrint(sb.toString());
  }

  /**
   * Mark the selected idx as undone.
   * 
   * @param idx Idx to be edited. Starting from 1.
   */
  public void markAsUndone(int idx) {
    Task entry = this.tasks.get(--idx);
    System.out.println(String.format("%s", Utility.INDENTED_LINE));
    if (entry.isDone()) {
      entry.markUndone();
      System.out.println(
          String.format("%sOK, I've marked this task as not done yet:", Utility.INDENT));
    } else {
      System.out.println(String.format("%sTask is already unmarked!", Utility.INDENT));
    }
    System.out.println(String.format("  %s%s", Utility.INDENT, entry.toString()));
    System.out.println(String.format("%s", Utility.INDENTED_LINE));
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
    return idx < this.tasks.size() && idx >= 0;
  }

  private static void prettyPrint(String msg) {
    System.out.println(Utility.INDENTED_LINE);
    System.out.println(msg);
    System.out.println(Utility.INDENTED_LINE);
  }
}
