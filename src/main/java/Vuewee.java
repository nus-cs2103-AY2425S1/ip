import java.util.Scanner;

public class Vuewee {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Task[] taskList = new Task[100];
    int taskListLength = 0;

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

      // List all tasks with done status if user types "list"
      if (input.equals("list")) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskListLength; i++) {
          Task task = taskList[i];
          System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
        }
        System.out.println("____________________________________________________________");
        continue;
      }

      // Mark task as done if user types "mark <task number>"
      if (input.startsWith("mark")) {
        String[] words = input.split(" ");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        taskList[taskNumber].markAsDone();

        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + taskList[taskNumber].getDescription());
        System.out.println("____________________________________________________________");
        continue;
      }

      // Mark task as undone if user types "unmark <task number>"
      if (input.startsWith("unmark")) {
        String[] words = input.split(" ");
        int taskNumber = Integer.parseInt(words[1]) - 1;
        taskList[taskNumber].markAsUndone();

        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + taskList[taskNumber].getDescription());
        System.out.println("____________________________________________________________");
        continue;
      }

      // Add task to task list
      taskList[taskListLength] = new Task(input);
      taskListLength++;

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
