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
    String logo = "  _____   _       _____ \n"
            + " | ____| | |     |__ __|\n"
            + " | |___  | |       |_|  \n"
            + " |  ___| | |       |_|  \n"
            + " | |___| | |___    |_|  \n"
            + " |_____| |_____| |_____|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println(" Hello! I'm Eli");
    System.out.println(" What are your tasks today?");
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
    System.out.println("An error occurred while loading tasks from file.");
  }

  /**
   * Displays a goodbye message to the user.
   */
  public static String showGoodbye() {
    return (" Bye. Come back soon!");
  }

  /**
   * Displays the search results of tasks containing a specific keyword.
   *
   * @param matchingTasks The list of tasks that match the search keyword.
   */
  public static String showFindResults(List<Task> matchingTasks) {
    StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
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

  public static String displayAfterAddTask(Task task) {
    return "OK. I've added this task: " + task;
  }

  public static String displayAfterDeleteTask(Task task) {
    return "OK. I've removed this task: " + task;
  }

  public static String displayAfterMarkTask(TaskList tasklist, int taskIdx) {
    StringBuilder result = new StringBuilder();
    result.append("Great job!\n");
    ArrayList tasks = tasklist.getArrayList();
    result.append("   ").append(tasks.get(taskIdx - 1).toString()).append("\n");
    return result.toString();
  }


  public static String displayAfterUnmarkTask(TaskList tasklist, int taskIdx) {
    StringBuilder result = new StringBuilder();
    result.append("OK, I've marked this task as not done yet.\n");
    ArrayList tasks = tasklist.getArrayList();
    result.append("   ").append(tasks.get(taskIdx - 1).toString()).append("\n");
    return result.toString();
  }

  public static String displayAfterListTask(TaskList tasklist) {
    StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
    ArrayList tasks = tasklist.getArrayList();
    for (int i = 0; i < tasks.size(); i++) {
      result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
    }
    return result.toString();
  }


}
