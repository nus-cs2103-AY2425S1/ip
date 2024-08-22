import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vuewee {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TaskList taskList = new TaskList();

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
          taskList.displayTasks();
          break;
        }

        // Mark task as done if user types "mark <task number>"
        case "mark": {
          try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            taskList.markTask(taskNumber, true);
          } catch (NumberFormatException e) {
            System.out.println("Invalid task number: " + inputParts[1]);
          }
          break;
        }

        case "unmark": {
          try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            taskList.markTask(taskNumber, false);
          } catch (NumberFormatException e) {
            System.out.println("Invalid task number: " + inputParts[1]);
          }
          break;
        }
        // Add todo task to task list
        // (Usage: todo <description>)
        case "todo": {
          taskList.addTask(new TodoTask(inputParts[1]));
          break;
        }
        // Add deadline task to task list
        // (Usage: deadline <description> /by <date>)
        case "deadline": {
          if (inputParts.length < 2) {
            System.out.println("Invalid deadline format. Usage: deadline <description> /by <date>");
            break;
          }

          String[] deadlineParts = inputParts[1].split(" /by ");
          if (deadlineParts.length != 2) {
            System.out.println(
                "Invalid deadline format. Usage: deadline <description> /by <date>");
            break;
          }
          taskList.addTask(new DeadlineTask(deadlineParts[0], deadlineParts[1]));
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

            taskList.addTask(new EventTask(description, fromDate, toDate));
          } else {
            System.out.println("Invalid event format. Usage: event <description> /from <fromDate> /to <toDate>");
          }
          break;
        }
        default: {
          System.out.println("Unknown command: " + command);
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
