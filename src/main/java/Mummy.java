import java.util.Scanner;

import command.Echo;
import command.Run;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class Mummy {
  private static final String LOGO =
        " __  __\n"
      + "|  \\/  |_   _ _ __ ___  _ __ ___  _   _\n" 
      + "| |\\/| | | | | '_ ` _ \\| '_ ` _ \\| | | |\n"
      + "| |  | | |_| | | | | | | | | | | | |_| |\n"
      + "|_|  |_|\\__,_|_| |_| |_|_| |_| |_|\\__, |\n"
      + "                                  |___/ \n";

  private static final Store<Task> STORE = new Store<>(100,
          "Here are the tasks in your list:");


  public static void main(String[] args) {
    new Echo("Hello from\n"
            + LOGO + "\n"
            + "What can I do for you?\n")
            .execute();
    Scanner scanner = new Scanner(System.in);
    listen(scanner);
    scanner.close();
  }

  public static void listen(Scanner scanner) {
    if (!scanner.hasNext()) {
      return;
    }

    String input = scanner.nextLine();
    String command = input.split(" ")[0];

    switch (command) {
      case "bye":
        onBye();
        return;
      case "list":
        onList();
        break;
      case "mark":
        onMark(input);
        break;
      case "unmark":
        onUnmark(input);
        break;
      case "todo":
        onToDo(input);
        break;
      case "deadline":
        onDeadline(input);
        break;
      case "event":
        onEvent(input);
        break;
      default:
        onAddTask(input);
    }

    listen(scanner);
  }

  private static void onBye() {
    new Echo("Bye. Hope to see you again soon!\n").execute();
  }

  private static void onList() {
    new Echo(STORE.toString()).execute();
  }

  private static void onMark(String input) {
    int taskIndex = parseIntOrDefault(
            input.replaceAll("[^0-9]", ""),
            -1);
    Task task = STORE.get(taskIndex);
    if (task == null) {
      new Echo("No such task").execute();
    } else {
      new Run(() -> "Nice! I've marked this task as done:\n\t" + task,
              task::setAsDone)
              .execute();
    }
  }

  private static void onUnmark(String input) {
    int taskIndex = parseIntOrDefault(
            input.replaceAll("[^0-9]", ""),
            -1);
    Task task = STORE.get(taskIndex);
    if (task == null) {
      new Echo("No such task").execute();
    } else {
      new Run(() -> "OK, I've marked this task as not done yet::\n\t" + task,
              task::setAsUndone)
              .execute();
    }
  }

  private static void onAddTask(String input) {
    Task task = new Task(input);
    new Run(() -> "added: " + task.getDescription(),
            () -> STORE.add(task))
            .execute();
  }

  private static void onToDo(String input) {
    // length of "todo " is 5
    String description = input.substring(5);
    Task task = new ToDo(description);
    new Run(() -> String.format(
            "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
            task, STORE.getCount()),
            () -> STORE.add(task))
            .execute();
  }

  private static void onDeadline(String input) {
    // length of "deadline " is 9
    String[] tokens = input.substring(9).split("\\s*/by\\s*");
    try {
      String description = tokens[0];
      String dueBy = tokens[1];
      Task task = new Deadline(description, dueBy);
      new Run(() -> String.format(
              "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
              task, STORE.getCount()),
              () -> STORE.add(task))
              .execute();
    } catch (IndexOutOfBoundsException exception) {
      new Echo(exception.getMessage()).execute();
    }
  }

  private static void onEvent(String input) {
    // length of "event " is 6
    String[] tokens = input.substring(6).split("\\s*/from\\s*");
    try {
      String description = tokens[0];
      String[] argumentTokens = tokens[1].split("\\s*/to\\s*");
      String from = argumentTokens[0];
      String to = argumentTokens[1];

      Task task = new Event(description, from, to);
      new Run(() -> String.format(
              "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
              task, STORE.getCount()),
              () -> STORE.add(task))
              .execute();
    } catch (IndexOutOfBoundsException exception) {
      new Echo(exception.getMessage()).execute();
    }
  }

  private static int parseIntOrDefault(String s, int defaultValue) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException exception) {
      return defaultValue;
    }
  }
}
