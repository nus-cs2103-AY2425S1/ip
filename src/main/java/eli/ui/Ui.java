package eli.ui;

import eli.task.Task;

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
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Eli");
    System.out.println(" What are your tasks today?");
    System.out.println("____________________________________________________________");
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
  public void showGoodbye() {
    System.out.println(" Bye. Come back soon!");
  }

  /**
   * Displays the search results of tasks containing a specific keyword.
   *
   * @param matchingTasks The list of tasks that match the search keyword.
   */
  public void showFindResults(List<Task> matchingTasks) {
    System.out.println("Here are the matching tasks in your list:");
    for (int i = 0; i < matchingTasks.size(); i++) {
      System.out.println((i + 1) + ". " + matchingTasks.get(i));
    }
  }

  /**
   * Closes the scanner used for input.
   */
  public void close() {
    scanner.close();
  }
}
