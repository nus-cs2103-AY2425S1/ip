import java.util.Scanner;

public class Mizz {
  /** Name of the chat bot */
  private static final String NAME = "Mizz";
  /** Constant len(13) line to be used for prettier printing */
  private static final String LINE = "-------------";

  /** Greeting to be printed */
  private final String greeting;
  /** ByeBye to be printed */
  private final String exitMsg;
  /** Last command entered by the user */
  private String cmd;

  public Mizz(String greeting, String exitMsg) {
    this.greeting = greeting;
    this.exitMsg = exitMsg;
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
   * @return The input cmd without whitespaces.
   */
  private String commandHandler(String cmd) {
    this.cmd = cmd.strip();
    switch (this.cmd) {
      case "bye":
        break;
      default:
        Mizz.prettyPrint(this.cmd);
        break;
    }
    return this.cmd;
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
   * Utility method to print a msg nicely within two horizontal lines.
   * 
   * @param msg The msg to be pretty printed.
   */
  private static void prettyPrint(String msg) {
    System.out.println(String.format("    %s", Mizz.LINE));
    System.out.println(String.format("    %s", msg));
    System.out.println(String.format("    %s", Mizz.LINE));
  }
}
