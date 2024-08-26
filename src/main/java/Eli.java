
import java.util.Scanner;
public class Eli {
  private TaskList taskList;
  private Scanner scanner;

  public Eli() {
    this.taskList = new TaskList();
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    String logo = "  _____   _       _____ \n"
            + " | ____| | |     |__ __|\n"
            + " | |___  | |       |_|  \n"
            + " |  ___| | |       |_|  \n"
            + " | |___| | |___    |_|  \n"
            + " |_____| |_____| |_____|\n";
    System.out.println("Hello from\n" + logo);

    // Greeting
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Eli");
    System.out.println(" What can I do for you?");
    System.out.println("____________________________________________________________");

    while (true) {

      String input = scanner.nextLine();

      if (input.equalsIgnoreCase("bye")) {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        break;
      }

      // Echo
      System.out.println("____________________________________________________________");
      System.out.println(" " + input);
      System.out.println("____________________________________________________________");

      if (input.equalsIgnoreCase("list")) {
        taskList.list();
      } else {
        taskList.addTask(input);
      }

    }
    scanner.close();
  }
  public static void main(String[] args) {
    Eli eli = new Eli();
    eli.start();
  }
}
