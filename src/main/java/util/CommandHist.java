package util;

import java.util.List;

import tasks.Task;
import tasks.ToDos;

import java.util.ArrayList;

public class CommandHist {
  private List<Task> commands;

  public CommandHist() {
    this.commands = new ArrayList<>(100);
  }

  /**
   * Method to add into the running list of commands entered.
   * 
   * @param taskType    Can be one of todo | deadline | event
   * @param taskDetails The info in the task
   * @return
   */
  public boolean addTask(String taskType, String taskDetails) {
    Task newTask;
    System.out.println(Utility.INDENTED_LINE);
    System.out.println(String.format("%sGot it I've added this task:", Utility.INDENT));
    switch (taskType) {
      case "todo":
        newTask = new ToDos(taskDetails);
        break;
      default:
        newTask = new Task("Hm whats this?");
        break;
    }
    System.out.println(String.format("%s%s", Utility.INDENT, newTask.toString()));
    System.out.println(String.format(
        "%sNow you have %d tasks in the list", Utility.INDENT, this.commands.size() + 1));
    System.out.println(Utility.INDENTED_LINE);
    return this.commands.add(newTask);
  }

  /**
   * Prints the items in the hist
   */
  public void prettyPrintAll() {
    int no = 1;
    System.out.println(Utility.INDENTED_LINE);
    System.out.println(String.format("%sHere are tasks in your list:", Utility.INDENT));
    for (Task cmd : this.commands) {
      System.out.println(String.format("%s%d. %s", Utility.INDENT, no++, cmd));
    }
    System.out.println(Utility.INDENTED_LINE);
  }

  /**
   * Mark the selected idx as done.
   * 
   * @param idx Idx to be edited. Starting from 1.
   */
  public void markAsDone(int idx) {
    Task entry = this.commands.get(--idx);
    System.out.println(Utility.INDENTED_LINE);
    if (!entry.isDone()) {
      entry.markDone();
      System.out.println(
          String.format("%sNice! I've marked this task as done:", Utility.INDENT));
    } else {
      System.out.println(String.format("%sTask has already been completed!", Utility.INDENT));
    }
    System.out.println(String.format("  %s%s", Utility.INDENT, entry.toString()));
    System.out.println(Utility.INDENTED_LINE);
  }

  /**
   * Mark the selected idx as undone.
   * 
   * @param idx Idx to be edited. Starting from 1.
   */
  public void markAsUndone(int idx) {
    Task entry = this.commands.get(--idx);
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

    for (Task cmd : this.commands) {
      sb.append(String.format("%d. %s\n", itemNo++, cmd));
    }
    return sb.toString();
  }
}
