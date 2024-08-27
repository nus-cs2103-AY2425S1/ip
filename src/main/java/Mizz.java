import java.util.Scanner;
import util.*;
import MizzExceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

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

  private final Ui ui;

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
    this.ui = new Ui();
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
      String cmd = bot.ui.getNextLine();
      bot.commandHandler(cmd);
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
    this.ui.printResponse(this.greeting);
  }

  /**
   * Static method to exit from the chatbot, prints a default exit message.
   */
  private void exit() {
    this.ui.printResponse(this.exitMsg);
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
      this.ui.printResponse(String.format(
          "%sSomeones tryna be funny, idx: %d is out of range!", Utility.INDENT, idx));
      return;
    }
    if (mark.equals("mark")) {
      Task t = this.usrTasks.markAsDone(idx, this.storage);
      if (t != null) {
        this.ui.printResponse("Nice! I've marked this task as done:", Utility.INDENT + t);
      } else {
        this.ui.printResponse("Task has already been completed!", Utility.INDENT + t);
      }
    } else {
      Task t = this.usrTasks.markAsUndone(idx, this.storage);
      if (t != null) {
        this.ui.printResponse("Ok, I've marked this task as not done yet:", Utility.INDENT + t);
      } else {
        this.ui.printResponse("Task is already unmarked!", Utility.INDENT + t);
      }
    }
  }

  /**
   * Method to create a new task.
   * 
   * @param taskType One of todo | deadline | event
   * @param taskInfo Array containing the description & by or from to depending.
   */
  private void handleCreate(String taskType, String[] taskInfo) throws MizzException {
    Task newTask;
    switch (taskType) {
      case "todo":
        newTask = new ToDo(taskInfo[1]);
        break;
      case "deadline":
        newTask = new Deadline(taskInfo[1], taskInfo[2]);
        break;
      case "event":
        newTask = new Event(taskInfo[1], taskInfo[2], taskInfo[3]);
        break;
      default:
        throw new MizzException("Invalid task type!");
    }
    this.usrTasks.addTask(newTask, this.storage);
    this.ui.printResponse("Got it I've added this task:", Utility.INDENT + newTask.toString(),
        String.format("You now have %d tasks in your list.", this.usrTasks.size()));
  }

  /**
   * Method to delete a task.
   * 
   * @param idx An idx starting from 1.
   */
  private void handleDelete(int idx) {
    if (!this.usrTasks.isValidIdx(idx)) {
      this.ui.printResponse(String.format(
          "%sSomeones tryna be funny, idx: %d is out of range!", Utility.INDENT, idx));
      return;
    }
    Task t = this.usrTasks.deleteTask(idx, this.storage);
    this.ui.printResponse("Ok! I've removed this task:", Utility.INDENT + t.toString(),
        String.format("You now have %d tasks in your list.", this.usrTasks.size()));
  }
}
