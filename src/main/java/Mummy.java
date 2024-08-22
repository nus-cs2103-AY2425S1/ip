import java.util.Scanner;

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

  private static final Store<Task> STORE = new Store<>("Here are the tasks in your list:");

  private enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE,
    EVENT, DELETE, UNKNOWN
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    greet();
    listen(scanner);
    scanner.close();
  }

  private static void greet() {
    echo("Hello from\n"
            + LOGO + "\n"
            + "What can I do for you?\n");
  }

  private static void listen(Scanner scanner) {
    while (scanner.hasNextLine()) {

      String input = scanner.nextLine();
      Command command;

      try {
        command = Command.valueOf(
                input.split(" ")[0].toUpperCase());
      } catch (IllegalArgumentException exception) {
        command = Command.UNKNOWN;
      }

      try {
        switch (command) {
          case BYE:
            onBye();
            return;
          case LIST:
            onList();
            break;
          case MARK:
            onMark(input);
            break;
          case UNMARK:
            onUnmark(input);
            break;
          case TODO:
            onToDo(input);
            break;
          case DEADLINE:
            onDeadline(input);
            break;
          case EVENT:
            onEvent(input);
            break;
          case DELETE:
            onDelete(input);
            break;
          default:
            throw new MummyException("I'm sorry, but I don't know what that means :-(");
        }
      } catch (MummyException exception) {
        echo("OOPS!!!! " + exception.getMessage());
      }
    }
  }

  private static void onBye() {
    echo("Bye. Hope to see you again soon!\n");
  }

  private static void onList() {
    echo(STORE.toString());
  }

  private static void onMark(String input) throws MummyException {
    int taskIndex = parseIntOrDefault(
            input.replaceAll("[^0-9]", ""),
            -1);

    try {
      Task task = STORE.get(taskIndex);
      task.setAsDone();
      echo("Nice! I've marked this task as done:\n\t" + task);
    } catch (StoreException exception) {
      throw new MummyException(exception.getMessage());
    }
  }

  private static void onUnmark(String input) throws MummyException {
    int taskIndex = parseIntOrDefault(
            input.replaceAll("[^0-9]", ""),
            -1);
    try {
      Task task = STORE.get(taskIndex);
      task.setAsUndone();
      echo("OK, I've marked this task as not done yet::\n\t" + task);
    } catch (StoreException exception) {
      throw new MummyException(exception.getMessage());
    }
  }

  private static void onToDo(String input) throws MummyException {
    // length of "todo " is 5
    try {
      String description = input.strip().substring(5);
      addTaskToStore(new ToDo(description));
    } catch (IndexOutOfBoundsException exception) {
      throw new MummyException("description cannot be empty");
    }
  }

  private static void onDeadline(String input) throws MummyException {
    try {
      // length of "deadline " is 9
      String[] tokens = input.substring(9).split("\\s*/by\\s*");
      String description = tokens[0];
      String dueBy = tokens[1];

      addTaskToStore(new Deadline(description, dueBy));
    } catch (IndexOutOfBoundsException exception) {
      throw new MummyException("Invalid argument: /by is required");
    }
  }

  private static void onEvent(String input) throws MummyException {
    try {
      // length of "event " is 6
      String[] tokens = input.substring(6).split("\\s*/from\\s*");
      String description = tokens[0];
      String[] argumentTokens = tokens[1].split("\\s*/to\\s*");
      String from = argumentTokens[0];
      String to = argumentTokens[1];

      addTaskToStore(new Event(description, from, to));
    } catch (IndexOutOfBoundsException exception) {
      throw new MummyException("Invalid argument: /from and /to are required");
    }
  }

  private static void onDelete(String input) throws MummyException {
    int taskIndex = parseIntOrDefault(
            input.replaceAll("[^0-9]", ""),
            -1);

    try {
      Task task = STORE.remove(taskIndex);
      echo(String.format(
              "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
              task, STORE.getCount()
              ));
    } catch (StoreException exception) {
      throw new MummyException(exception.getMessage());
    }
  }

  private static void addTaskToStore(Task task) {
    STORE.add(task);
    echo(String.format(
            "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
            task, STORE.getCount()
            ));
  }

  private static void echo(String message) {
    new Echo(message).execute();
  }

  private static int parseIntOrDefault(String s, int defaultValue) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException exception) {
      return defaultValue;
    }
  }
}
