import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.err.println("You have no tasks in your list.");
            break;
          }

          for (int i = 0; i < taskListLength; i++) {
            Task task = taskList[i];
            System.out.println((i + 1) + ". " + task.toString());
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
        // Add todo task to task list
        // (Usage: todo <description>)
        case "todo": {
          taskList[taskListLength] = new TodoTask(inputParts[1]);
          taskListLength++;
          System.out.println("Got it. I've added this task:");
          System.out.println("  " + taskList[taskListLength - 1]);
          break;
        }
        // Add deadline task to task list
        // (Usage: deadline <description> /by <date>)
        case "deadline": {
          if (inputParts.length < 2) {
            System.err.println("Invalid deadline format. Usage: deadline <description> /by <date>");
            break;
          }

          String[] deadlineParts = inputParts[1].split(" /by ");
          if (deadlineParts.length != 2) {
            System.err.println(
                "Invalid deadline format. Usage: deadline <description> /by <date>");
            break;
          }
          taskList[taskListLength] = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
          System.out.println("Got it. I've added this task:");
          System.out.println("  " + taskList[taskListLength]);
          taskListLength++;
          System.out.println("Now you have " + taskListLength + " tasks in the list.");
          break;
        }
        // Add event task to task list
        // (Usage: event <description> /from <fromDate> /to <toDate>)
        case "event": {
          if (inputParts.length < 2) {
            System.out.println("Invalid event format. Usage: event <description> /from <fromDate> /to <toDate>");
            break;
          }

          String paramsInput = inputParts[1];
          Pattern fromPattern = Pattern.compile("/from (.+?)(?:$| /to| /from)");
          Pattern toPattern = Pattern.compile("/to (.+?)(?:$| /from| /to)");

          Matcher fromMatcher = fromPattern.matcher(paramsInput);
          Matcher toMatcher = toPattern.matcher(paramsInput);

          if (fromMatcher.find() && toMatcher.find()) {
            // Extract description, from date and to date using regex results
            String description = paramsInput.substring(0, Math.min(fromMatcher.start(), toMatcher.start())).trim();
            String fromDate = fromMatcher.group(1);
            String toDate = toMatcher.group(1);

            taskList[taskListLength] = new EventTask(description, fromDate, toDate);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + taskList[taskListLength]);
            taskListLength++;
            System.out.println("Now you have " + taskListLength + " tasks in the list.");
          } else {
            System.err.println("Invalid event format. Usage: event <description> /from <fromDate> /to <toDate>");
          }
          break;
        }
        default: {
          System.err.println("Unknown command: " + command);
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
