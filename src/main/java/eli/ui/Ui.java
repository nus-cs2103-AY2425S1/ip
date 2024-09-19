package eli.ui;

import eli.task.Task;
import eli.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with the user, including reading input and displaying messages.
 */
public class Ui {
  private Scanner scanner;

  /**
   * Constructor for Ui.
   */
  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Reads the user's command from input.
   *
   * @return The user's input command.
   */
  public String readCommand() {
    return scanner.nextLine().trim();
  }

  /**
   * Displays the welcome message to the user.
   */
  public void showWelcome() {
    System.out.println(" \"Hi there! \uD83C\uDF38 I'm Eli, your assistant! What can I do for you today? \uD83D\uDC96\"");
  }

  /**
   * Displays an error message to the user.
   *
   * @param message The error message to be displayed.
   */
  public void showError(String message) {
    System.out.println("Error: " + message);
  }

  /**
   * Displays a loading error message.
   */
  public void showLoadingError() {
    System.out.println("Oopsie! \uD83D\uDE05 An error occurred while loading tasks from file! Could you try again, please? \uD83D\uDC96");
  }

  /**
   * Displays a goodbye message to the user.
   */
  public static String showGoodbye() {
    return (" Goodbye, sweet friend! Can’t wait to see you again soon! \uD83D\uDC95✨");
  }

  /**
   * Displays the search results of tasks containing a specific keyword.
   *
   * @param matchingTasks The list of tasks that match the search keyword.
   */
  public static String showFindResults(List<Task> matchingTasks) {
    StringBuilder result = new StringBuilder("Here are the matching tasks in your list \uD83C\uDF38:\n");
    for (int i = 0; i < matchingTasks.size(); i++) {
      result.append((i + 1)).append(". ").append(matchingTasks.get(i).toString()).append("\n");
    }
    return result.toString();
  }

  /**
   * Closes the scanner used for input.
   */
  public void close() {
    scanner.close();
  }

  /**
   * Displays a message after a task has been added.
   *
   * @param task The task that was added.
   * @return A string displaying a confirmation message for the added task.
   */
  public static String displayAfterAddTask(Task task) {
    return "Yay! \uD83C\uDF37 I’ve added '" + task + "' just for you! Keep up the great work! \uD83D\uDCAA✨ ";
  }

  /**
   * Displays a message after a task has been deleted.
   *
   * @param task The task that was deleted.
   * @return A string displaying a confirmation message for the deleted task.
   */
  public static String displayAfterDeleteTask(Task task) {
    return "Alright, now '" + task + "' is gone! ✨ You're doing amazing, keep it up!! \uD83C\uDF08";
  }

  /**
   * Displays a message after marking a task as done.
   *
   * @param tasklist The list of tasks.
   * @param taskIdx  The index of the task that was marked as done.
   * @return A string displaying a confirmation message for the marked task.
   */
  public static String displayAfterMarkTask(TaskList tasklist, int taskIdx) {
    StringBuilder result = new StringBuilder();
    result.append("Yay! \uD83C\uDF1F You did it! So proud of you! \uD83C\uDF89 Keep going! \uD83D\uDCAA\n");
    ArrayList tasks = tasklist.getArrayList();
    result.append("   ").append(tasks.get(taskIdx - 1).toString()).append("\n");
    return result.toString();
  }

  /**
   * Displays a message after unmarking a task as not done.
   *
   * @param tasklist The list of tasks.
   * @param taskIdx  The index of the task that was unmarked.
   * @return A string displaying a confirmation message for the unmarked task.
   */
  public static String displayAfterUnmarkTask(TaskList tasklist, int taskIdx) {
    StringBuilder result = new StringBuilder();
    result.append("Ok. I've marked this task as not done yet. Come back soon! \uD83C\uDF38 \n");
    ArrayList tasks = tasklist.getArrayList();
    result.append("   ").append(tasks.get(taskIdx - 1).toString()).append("\n");
    return result.toString();
  }

  /**
   * Displays the list of tasks.
   *
   * @param tasklist The list of tasks to display.
   * @return A string displaying all tasks in the list.
   */
  public static String displayAfterListTask(TaskList tasklist) {
    StringBuilder result = new StringBuilder("\"Here are the tasks in your list ✨:\n");
    ArrayList tasks = tasklist.getArrayList();
    for (int i = 0; i < tasks.size(); i++) {
      result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
    }
    return result.toString();
  }


}
