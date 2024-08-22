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

      String[] inputParts = input.split(" ", 2);

      if (inputParts.length == 0) {
        throw new Error("Unknown error occured. Input error.");
      }

      String command = inputParts[0];

      // Exit the loop if user types "bye"
      if (command.equals("bye")) {
        break;
      }

      System.out.println("____________________________________________________________");
      switch (command) {
        // List all tasks with done status if user types "list"
        case "list": {
          if (taskListLength == 0) {
            System.out.println("You have no tasks in your list.");
            break;
          }

          for (int i = 0; i < taskListLength; i++) {
            Task task = taskList[i];
            System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
          }
          break;
        }

        // Mark task as done if user types "mark <task number>"
        case "mark": {
          try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;

            if (taskNumber >= taskListLength || taskNumber < 0) {
              System.out.println("Invalid task number. There are " + taskListLength + " tasks in your list.");
              break;
            }

            boolean success = taskList[taskNumber].markAsDone();

            System.out.println(success ? "Nice! I've marked this task as done:" : "This task is already done:");
            System.out.println("  " + taskList[taskNumber].toString());
          } catch (NumberFormatException e) {
            System.err.println("Invalid task number: " + inputParts[1]);
          }
          break;
        }

        case "unmark": {
          try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;

            if (taskNumber >= taskListLength || taskNumber < 0) {
              System.out.println("Invalid task number. There are " + taskListLength + " tasks in your list.");
              break;
            }

            boolean success = taskList[taskNumber].markAsUndone();

            System.out.println(
                success ? "OK, I've marked this task as not done yet:" : "This task is already marked as not done:");
            System.out.println("  " + taskList[taskNumber].toString());
          } catch (NumberFormatException e) {
            System.err.println("Invalid task number: " + inputParts[1]);
          }
          break;
        }
        // Add TODO task to task list
        case "todo": {
          taskList[taskListLength] = new TodoTask(inputParts[1]);
          taskListLength++;
          System.out.println("Got it. I've added this task:");
          System.out.println("  " + taskList[taskListLength - 1]);
          break;
        }
        default: {
          taskList[taskListLength] = new Task(input, ' ');
          taskListLength++;
          System.out.println("added: " + input);
          break;
        }
      }
      System.out.println("____________________________________________________________");
    }

    System.out.println("____________________________________________________________");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("____________________________________________________________");
    scanner.close();
  }
}
