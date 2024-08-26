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

      String input = scanner.nextLine().trim();
      String[] inputParts = input.split(" ", 2); // mark 2 // unmark 2
      String instruction = inputParts[0];

      if (instruction.equalsIgnoreCase("bye")) {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        break;
      }

      // Echo
      //System.out.println("____________________________________________________________");
      //System.out.println(" " + input);
      //System.out.println("____________________________________________________________");

      if (instruction.equalsIgnoreCase("list")) {
        taskList.list();
      } else if (instruction.equalsIgnoreCase("mark")) {
        String taskIdxStr = inputParts[1];
        int taskIdx = Integer.valueOf(taskIdxStr);
        taskList.mark(taskIdx);
      } else if (instruction.equalsIgnoreCase("unmark")) {
        String taskIdxStr = inputParts[1];
        int taskIdx = Integer.valueOf(taskIdxStr);
        taskList.unmark(taskIdx);
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
