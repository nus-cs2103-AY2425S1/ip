import java.util.Arrays;
import java.util.Scanner;
import util.*;
import MizzExceptions.*;

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
    String greeting = String.format(
        "Hello! I'm %s\n%sWhat can I do for you?", NAME, Utility.INDENT);
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
    try {
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
   * Utility method to parse and clean the user input.
   * 
   * @param in The input from the scanner.
   * @return
   */
  private String[] parseInput(String inpuString) throws MizzException {
    // parts[0] -> command
    // parts[1] -> description
    // parts[2] -> "" if todo | by if deadline | from if event
    // parts[3] -> to if event else ""

    String[] result = new String[4];
    String[] parts = inpuString.split("\\s+");
    result[0] = parts[0].toLowerCase();

    if (result[0].equals("mark") || result[0].equals("unmark")) {
      verifyMarkUnmark(parts);
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
      verifyTodo(parts);
      result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
    } else if (result[0].equals("deadline")) {
      verifyDeadline(parts, byIdx);
      result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, byIdx));
      result[2] = String.join(" ", Arrays.copyOfRange(parts, byIdx + 1,
          parts.length));
    } else if (result[0].equals("event")) {
      verifyEvent(parts, fromIdx, toIdx);
      result[1] = String.join(" ", Arrays.copyOfRange(parts, 1, fromIdx));
      result[2] = String.join(" ", Arrays.copyOfRange(parts, fromIdx + 1, toIdx));
      result[3] = String.join(" ", Arrays.copyOfRange(parts, toIdx + 1, parts.length));
    }

    return result;
  }

  /**
   * Method to validate the todo command.
   * 
   * @param details The full split input from the user.
   * @throws ToDoException if the input is malformed.
   */
  private void verifyTodo(String[] details) throws ToDoException {
    if (details.length == 1) {
      throw new ToDoException("Missing a descrption for this todo!");
    }
  }

  /**
   * Method to validate the deadline command.
   * 
   * @param details The full split input from the user.
   * @param byIdx   The idx of "/by" in details.
   * @throws DeadlineException if the input is malformed.
   */
  private void verifyDeadline(String[] details, int byIdx) throws DeadlineException {
    int maxAllowableIdx = details.length - 1;
    if (byIdx == -1) {
      throw new DeadlineException("Missing /by in the input!");
    }
    if (byIdx == maxAllowableIdx) {
      throw new DeadlineException("Missing a string behind /by... by when?");
    }
    if (byIdx == 1) {
      throw new DeadlineException(
          "Hmmm I'm not sure whats the deadline for? Missing description!");
    }
  }

  /**
   * Method to validate the event command.
   * 
   * @param details The full split input from the user.
   * @param fromIdx The idx of "/from" in details.
   * @param toIdx   The idx of "/to" in details.
   * @throws EventException if the input is malformed.
   */
  private void verifyEvent(String[] details, int fromIdx, int toIdx) throws EventException {
    int maxAllowableIdx = details.length - 1;
    if (fromIdx == 1) {
      throw new EventException("Hmmm what's this event about? Missing description!");
    }
    if (fromIdx == -1) {
      throw new EventException("Missing /from in the input!");
    }
    if (toIdx == -1) {
      throw new EventException("To infinity! Missing /to in the input!");
    }
    if (fromIdx > toIdx) {
      throw new EventException("/from should come before /to!");
    }
    if (fromIdx + 1 >= toIdx) {
      throw new EventException("Missing a string behind /from... from when?");
    }
    if (toIdx == maxAllowableIdx) {
      throw new EventException("Hm when does this event end? Missing info behind /to");
    }
  }

  /**
   * Method to validate the mark or unmark command.
   * 
   * @param details The full split input from the user.
   * @throws UpdateMarkedException if the input is malformed.
   */
  private void verifyMarkUnmark(String[] details) throws UpdateMarkedException {
    if (details.length == 1) {
      throw new UpdateMarkedException("Too few arguments missing idx to mark/ unmark");
    }
    try {
      Integer.parseInt(details[1]);
    } catch (NumberFormatException e) {
      throw new UpdateMarkedException(
          String.format("Last I checked (%s)'s no int :/", details[1]));
    }
  }
}
