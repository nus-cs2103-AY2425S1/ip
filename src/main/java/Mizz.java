import java.util.Arrays;
import java.util.Scanner;
import util.*;

public class Mizz {
  /** Name of the chat bot */
  private static final String NAME = "Mizz";

  /** Stores the past commands entered */
  private final TaskHist usrCmds;
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
    this.usrCmds = new TaskHist();
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
    String[] parts = cmd.split("\\s+");
    this.cmd = parts[0];
    switch (this.cmd) {
      case "bye":
        break;
      case "list":
        this.usrCmds.prettyPrintAll();
        break;
      case "mark":
      case "unmark": {
        // look at second arg ignore the rest
        int idx = Integer.parseInt(parts[1]);
        this.handleMark(this.cmd, idx);
        break;
      }
      case "todo":
      case "deadline":
      case "event": {
        this.handleCreate(this.cmd, Arrays.copyOfRange(parts, 1, parts.length));
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
    if (mark.equals("mark")) {
      this.usrCmds.markAsDone(idx);
    } else {
      this.usrCmds.markAsUndone(idx);
    }
  }

  /**
   * 
   * @param msg
   */
  private void handleCreate(String taskType, String[] taskInfo) {
    String cleanedString = String.join(" ", taskInfo);
    this.usrCmds.addTask(taskType, cleanedString);
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
}
