package elon;

import elon.task.Task;
import elon.task.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

/** Provides user interface functions for interacting with the task list and its tasks. */
public class Ui {
  private Scanner scanner;

  /** Initializes the Ui object and sets up the scanner for user input. */
  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Draws a horizontal line in the console for separation.
   *
   * @return a string representing a horizontal line
   */
  public String drawLine() {
    return "-------------------------------------------------------\n";
  }

  /**
   * Prints a greeting message to the console.
   *
   * @return a greeting message
   */
  public static String greetMessage() {
    return "Hello! I'm Elon\n" + "What can I do for you?\n";
  }

  /**
   * Prints a goodbye message to the console.
   *
   * @return a string goodbye message
   */
  public String exitMessage() {
    return "Bye. Hope to see you again soon!";
  }

  /**
   * Gets user input as an array of strings.
   *
   * @return an array of strings containing the user input
   */
  public String[] getInputArr() {
    return scanner.nextLine().split(" ");
  }

  /**
   * Displays a message indicating an invalid index.
   *
   * @return a string message for an invalid index
   */
  public String showInvalidIndex() {
    return "Index out of range.\n";
  }

  /**
   * Prints the tasks in the list to the console. If the list is empty, prints a message indicating
   * that there are no tasks.
   *
   * @param list the TaskList containing tasks to be displayed
   * @return a string representation of the tasks in the list
   */
  public String listTasks(TaskList list) {
    if (list.isEmpty()) {
      return "There are no tasks in your list.\n";
    } else {
      String res = "Here are the tasks in your list:\n";
      for (int i = 0; i < list.listSize(); i++) {
        res += String.format("%d.", i + 1) + list.getTask(i).toString() + "\n";
      }
      return res;
    }
  }

  /**
   * Marks the specified task as done and returns a message.
   *
   * @param task the task to mark as done
   * @return a string message indicating the task has been marked as done
   */
  public String markTask(Task task) {
    return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
  }

  /**
   * Marks the specified task as not done and returns a message.
   *
   * @param task the task to mark as not done
   * @return a string message indicating the task has been marked as not done
   */
  public String unmarkTask(Task task) {
    return "OK, I've marked this task as not done yet:\n" + task.toString() + "\n";
  }

  /**
   * Starts the task addition process and returns a message.
   *
   * @return a string message indicating a task has been added
   */
  public String startAddTask() {
    return "Got it. I've added this task:\n";
  }

  /**
   * Returns a message indicating the updated number of tasks in the list after adding a new task.
   *
   * @param size the updated number of tasks in the list
   * @return a string message with the current task count
   */
  public String endAddTask(int size) {
    return String.format("Now you have %d tasks in the list.\n", size);
  }

  /**
   * Displays a message when adding a ToDo task to the list.
   *
   * @param task the task being added
   * @param list the TaskList to add the task to
   * @return a string message indicating the task has been added
   */
  public String addTask(Task task, TaskList list) {
    return startAddTask() + task.toString() + "\n" + endAddTask(list.listSize());
  }

  /**
   * Deletes the specified task from the task list and returns a message.
   *
   * @param task the task to delete
   * @param list the TaskList containing the task
   * @return a string message indicating the task has been deleted
   */
  public String deleteTask(Task task, TaskList list) {
    return "Noted. I've removed this task:\n"
        + task.toString()
        + "\n"
        + endAddTask(list.listSize());
  }

  /**
   * Displays the tasks that match the given keyword.
   *
   * @param matchingTasks the list of tasks that match the keyword
   * @return a string representation of the matching tasks
   */
  public String showMatchingTasks(ArrayList<Task> matchingTasks) {
    if (matchingTasks.isEmpty()) {
      return "No matching tasks found.\n";
    } else {
      String res = "Here are the matching tasks in your list:\n";
      for (int i = 0; i < matchingTasks.size(); i++) {
        res += String.format("%d.%s\n", i + 1, matchingTasks.get(i).toString());
      }
      return res;
    }
  }

  /**
   * Displays a message indicating a task has been snoozed.
   *
   * @param task the task that has been snoozed
   * @return a string message indicating the task has been snoozed
   */
  public String snoozeTask(Task task) {
    return "Nice! I've snoozed this task:\n" + task.toString() + "\n";
  }
}
