import java.util.Scanner;
import util.*;
import MizzExceptions.*;

public class Mizz {
  /** Name of the chat bot */
  private static final String NAME = "Mizz";

  /** Stores the past commands entered */
  private final TaskList usrTasks;
  /** Greeting to be printed */
  private final String greeting;
  /** ByeBye to be printed */
  private final String exitMsg;
  /** Last command entered by the user */
  private String cmd;

  private final Storage storage;

  /**
   * Constructor for Mizz class. Initialises the Mizz object with defualt values
   * 
   * @param greeting The greeting msg to be printed.
   * @param exitMsg  The exit msg to be printed.
   */
  public Mizz(String greeting, String exitMsg, String filePath) {
    this.greeting = greeting;
    this.exitMsg = exitMsg;
    this.usrTasks = new TaskList();
    this.cmd = "";
    this.storage = new Storage(filePath);
    try {
      Parser.parseFromStorage(this.storage).forEach((t) -> {
        this.usrTasks.addTask(t);
      });
    } catch (MizzException e) {
      System.err.println("Error adding task from file: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    String greeting = String.format(
        "Hello! I'm %s\n%sWhat can I do for you?", NAME, Utility.INDENT);
    String exitMsg = "Bye. Hope to see you again soon!";
    Mizz bot = new Mizz(greeting, exitMsg, "./store/storage.txt");
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
    try {
      String[] parsedInput = Parser.parseStringInput(cmd);
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
        case "delete": {
          int idx = Integer.parseInt(parsedInput[1]);
          this.handleDelete(idx);
          break;
        }
        case "todo":
        case "deadline":
        case "event": {
          this.handleCreate(this.cmd, parsedInput);
          break;
        }
        default:
          throw new MizzException("Invalid input: (" + this.cmd + ")");
        // break;
      }
    } catch (MizzException e) {
      Utility.prettyPrint(e.toString());
    }
  }

  /**
   * Static method to print greeting.
   */
  private void greet() {
    Utility.prettyPrint(String.format("%s%s", Utility.INDENT, this.greeting));
  }

  /**
   * Static method to exit from the chatbot, prints a default exit message.
   */
  private void exit() {
    Utility.prettyPrint(String.format("%s%s", Utility.INDENT, this.exitMsg));
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
      Utility.prettyPrint(String.format(
          "%sSomeones tryna be funny, idx: %d is out of range!", Utility.INDENT, idx));
      return;
    }
    if (mark.equals("mark")) {
      this.usrTasks.markAsDone(idx, this.storage);
    } else {
      this.usrTasks.markAsUndone(idx, this.storage);
    }
  }

  /**
   * Method to create a new task.
   * 
   * @param taskType One of todo | deadline | event
   * @param taskInfo Array containing the description & by or from to depending.
   */
  private void handleCreate(String taskType, String[] taskInfo) {
    this.usrTasks.addTask(taskType, taskInfo, this.storage);
  }

  /**
   * Method to delete a task.
   * 
   * @param idx An idx starting from 1.
   */
  private void handleDelete(int idx) {
    if (!this.usrTasks.isValidIdx(idx)) {
      Utility.prettyPrint(String.format(
          "%sSomeones tryna be funny, idx: %d is out of range!", Utility.INDENT, idx));
      return;
    }
    this.usrTasks.deleteTask(idx, this.storage);
  }
}
