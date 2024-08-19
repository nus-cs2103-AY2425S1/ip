import java.util.Scanner;

import command.*;

public class Mummy {
  private static final String LOGO =  " __  __\n"
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
        new Echo("Bye. Hope to see you again soon!\n").execute();
        return;
      case "list":
        new Echo(STORE.toString()).execute();
        break;
      case "mark": {
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
        break;
      }
      case "unmark": {
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
        break;
      }
      default: {
        Task task = new Task(input);
        new Run(() -> "added: " + task.getDescription(),
                () -> STORE.add(task))
                .execute();
      }
    }

    listen(scanner);
  }

  private static int parseIntOrDefault(String s, int defaultValue) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException exception) {
      return defaultValue;
    }
  }
}
