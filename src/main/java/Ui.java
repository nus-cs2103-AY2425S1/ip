import java.util.Scanner;

public class Ui {
  private Scanner scanner;

  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  public String readCommand() {
    return scanner.nextLine().trim();
  }

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

  public void showError(String message) {
    System.out.println("Error: " + message);
  }

  public void showLoadingError() {
    System.out.println("An error occurred while loading tasks from file.");
  }

  public void showGoodbye() {
    System.out.println(" Bye. Come back soon!");
  }

  public void close() {
    scanner.close();
  }
}
