import java.util.Arrays;
import java.util.Scanner;
import util.*;

public class Mizz {
  /** Name of the chat bot */
  private static final String NAME = "Mizz";

  /** Stores the past commands entered */
  private final TaskHist usrTasks;
  /** Greeting to be printed */
  private final String greeting;
  /** ByeBye to be printed */
  private final String exitMsg;
  /** Last command entered by the user */
  private String cmd;

  /**
   * Constructor for Mizz class. Initialises the Mizz object with defualt values
   * 
   * @param greeting The greeting msg to be printed.
   * @param exitMsg  The exit msg to be printed.
   */
  public Mizz(String greeting, String exitMsg) {
    this.greeting = greeting;
    this.exitMsg = exitMsg;
    this.usrTasks = new TaskHist();
    this.cmd = "";
  }

  public static void main(String[] args) {
    String greeting = String.format("Hello! I'm %s\n    What can I do for you?", NAME);
    String exitMsg = "Bye. Hope to see you again soon!";
    Mizz bot = new Mizz(greeting, exitMsg);
    Scanner scanner = new Scanner(System.in);

    bot.greet();

    while (!bot.isExited()) {
      bot.commandHandler(scanner.nextLine());
    }
    bot.exit();

    scanner.close();
  }

  /**
   * Static method to parse and handle commands input by the user.
   * 
   * @param cmd The command read by scanner.
   */
  private void commandHandler(String cmd) {
    String[] parsedInput = this.parseInput(cmd);
    this.cmd = parsedInput[0];
    switch (this.cmd) {
      case "bye":
        break;
      case "list":
        this.usrTasks.prettyPrintAll();
        break;
      case "mark":
      case "unmark": {
        // look at second arg ignore the rest
        int idx = Integer.parseInt(parsedInput[1]);
        this.handleMark(this.cmd, idx);
        break;
      }
      case "todo":
      case "deadline":
      case "event": {
        this.handleCreate(this.cmd, parsedInput);
        break;
      }
      default:
        Mizz.prettyPrint("Invalid command!");
        break;
    }
  }

  /**
   * Static method to print greeting.
   */
  private void greet() {
    Mizz.prettyPrint(this.greeting);
  }

  /**
   * Static method to exit from the chatbot, prints a default exit message.
   */
  private void exit() {
    Mizz.prettyPrint(this.exitMsg);
  }

  /**
   * Utility method to check if the bot should be exited.
   * 
   * @return true if cmd == "bye" else false
   */
  private boolean isExited() {
    return this.cmd.equals("bye");
  }

  /**
   * Utility method to update the done status of a task.
   * 
   * @param mark The command which can be mark or unmark.
   * @param idx  The idx of the task in the list starting from 1.
   */
  private void handleMark(String mark, int idx) {
    // if invalid return after printing help text
    if (!this.usrTasks.isValidIdx(idx)) {
      Mizz.prettyPrint(String.format(
          "Someones tryna be funny, idx: %d is out of range!", idx));
      return;
    }
    if (mark.equals("mark")) {
      this.usrTasks.markAsDone(idx);
    } else {
      this.usrTasks.markAsUndone(idx);
    }
  }

  /**
   * Method to create a new task.
   * 
   * @param taskType One of todo | deadline | event
   * @param taskInfo Array containing the description & by or from to depending.
   */
  private void handleCreate(String taskType, String[] taskInfo) {
    this.usrTasks.addTask(taskType, taskInfo);
  }

  /**
   * Utility method to print a msg nicely within two horizontal lines.
   * 
   * @param msg The msg to be pretty printed.
   */
  private static void prettyPrint(String msg) {
    System.out.println(Utility.INDENTED_LINE);
    System.out.println(String.format("%s%s", Utility.INDENT, msg));
    System.out.println(Utility.INDENTED_LINE);
  }

  /**
   * Utility method to parse and clean the user input.
   * 
   * @param in The input from the scanner.
   * @return
   */
  private String[] parseInput(String inpuString) {
    // parts[0] -> command
    // parts[1] -> description
    // parts[2] -> "" if todo | by if deadline | from if event
    // parts[3] -> to if event else ""

    String[] result = new String[4];
    String[] parts = inpuString.split("\\s+");
    result[0] = parts[0].toLowerCase();

    if (result[0].equals("mark") || result[0].equals("unmark")) {
      if (parts.length < 2) {
        // throw exception
      }
      result[1] = parts[1];
      return result;
    }

    if (result[0].equals("list") || result[0].equals("bye")) {
      return result;
    }

    int byIdx = -1;
    int fromIdx = -1;
    int toIdx = -1;

    // only take the first occurence of /by | /from | /to
    for (int i = 1; i < parts.length; i++) {
      if (fromIdx == -1 && parts[i].equals("/from")) {
        fromIdx = i;
      } else if (toIdx == -1 && parts[i].equals("/to")) {
        toIdx = i;
      } else if (byIdx == -1 && parts[i].equals("/by")) {
        byIdx = i;
      }
    }
    // description
    if (result[0].equals("todo")) {
      result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
    } else if (result[0].equals("deadline")) {
      if (byIdx == -1 || byIdx == parts.length - 1) {
        // throw exception
      }
      result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, byIdx));
      result[2] = String.join(" ", Arrays.copyOfRange(parts, byIdx + 1, parts.length));
    } else if (result[0].equals("event")) {
      if (fromIdx == -1 || toIdx == -1 || fromIdx + 1 >= toIdx) {
        // throw exception
      }
      result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, fromIdx));
      result[2] = String.join(" ", Arrays.copyOfRange(parts, fromIdx + 1, toIdx));
      result[3] = String.join(" ", Arrays.copyOfRange(parts, toIdx + 1, parts.length));
    }

    return result;
  }
}
