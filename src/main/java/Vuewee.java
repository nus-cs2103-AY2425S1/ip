import java.util.Scanner;

public class Vuewee {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String[] taskList = new String[100];
    int taskListIndex = 0;

    System.out.println("____________________________________________________________");
    System.out.println("Hello! I'm Vuewee\nWhat can I do for you?");
    System.out.println("____________________________________________________________");

    // Echo input from user until user types "bye"
    while (true) {
      String input = scanner.nextLine();

      // Exit the loop if user types "bye"
      if (input.equals("bye")) {
        break;
      }

      // List all tasks if user types "list"
      if (input.equals("list")) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskList.length; i++) {
          if (taskList[i] != null) {
            System.out.println(i + 1 + ". " + taskList[i]);
          }
        }
        System.out.println("____________________________________________________________");
        continue;
      }

      // Add task to task list
      taskList[taskListIndex] = input;
      taskListIndex++;

      System.out.println("____________________________________________________________");
      System.out.println("added: " + input);
      System.out.println("____________________________________________________________");
    }

    System.out.println("____________________________________________________________");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("____________________________________________________________");
    scanner.close();
  }
}
