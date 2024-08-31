package ui;

public class Ui {

    private final String line = "____________________________________________________________\n";

    public Ui() {}
    /**
     * Prints the greeting to the user.
     */
    public void greet() {
        System.out.print(line() + "Hello! I'm Meeks! Your friendly chatbot!\n" + "What can I do for you?\n");
    }
    /**
     * Prints the goodbye message to the user.
     */
    public void goodbye() {
        System.out.print("Bye. Hope to see you again soon!\n" + line());
    }
    /**
     * Prints the unknown command message to the user.
     */
    public void invalidCommand() {
        System.out.print("Oh no! You have input an unknown command!\n" + line());
    }
    /**
     * A line to separate messages.
     * @return a string message of the segmentation between messages.
     */
    public String line() {
        return this.line;
    }
    /**
     * A message to denote successful addition of the task to the tasklist.
     * @return a string to affirm the user.
     */
    public String affirm() {
        return "Got it. I've added this task: \n";
    }
}
