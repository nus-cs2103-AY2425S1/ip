import java.util.Scanner;

public class Mizz {
  /** Name of the chat bot */
  private static final String NAME = "Mizz";
  /** Constant len(13) line to be used for prettier printing */
  private static final String LINE = "-------------";

  public static void main(String[] args) {
    String greeting = String.format("Hello! I'm %s\n    What can I do for you?", NAME);
    String cmd = "";
    Mizz.greet(greeting);

    Scanner scanner = new Scanner(System.in);

    while (!cmd.equals("bye")) {
      cmd = Mizz.commandHandler(scanner.nextLine());
    }
    Mizz.exit();

    scanner.close();
  }

  /**
   * Static method to parse and handle commands input by the user.
   * 
   * @param cmd The command read by scanner.
   * @return The input cmd without whitespaces.
   */
  private static String commandHandler(String cmd) {
    cmd = cmd.strip();
    switch (cmd) {
      case "bye":
        break;
      default:
        Mizz.prettyPrint(cmd);
        break;
    }
    return cmd;
  }

  /**
   * Static method to print greeting.
   * 
   * @param greeting The custom greeting to print when method is invoked.
   */
  private static void greet(String greeting) {
    Mizz.prettyPrint(greeting);
  }

  /**
   * Static method to exit from the chatbot, prints a default exit message.
   */
  private static void exit() {
    Mizz.prettyPrint("Bye. Hope to see you again soon!");
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
