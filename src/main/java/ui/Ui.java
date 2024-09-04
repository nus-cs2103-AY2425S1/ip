package ui;

public class Ui {

    private final String line = "_______________________________________________________\n";

    public Ui() {}
    /**
     * Prints the greeting to the user.
     */
    public String greet() {
        return line() + "Hello! I'm Meeks! Your friendly chatbot!\n" + "What can I do for you?\n";
    }
    /**
     * Prints the goodbye message to the user.
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!\n" + line();
    }
    /**
     * Prints the unknown command message to the user.
     */
    public String invalidCommand() {
        return "Oh no! You have input an unknown command!\n" + line();
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
