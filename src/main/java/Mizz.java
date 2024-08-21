public class Mizz {
  private static final String NAME = "Mizz";
  private static final String LINE = "-------------";

  public static void main(String[] args) {
    String greeting = String.format("Hello! I'm %s\n    What can I do for you?", NAME);
    Mizz.greet(greeting);
    Mizz.exit();
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
