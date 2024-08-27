package friendlybot;

/**
 * Ui deals with the interactions between FriendlyBot and the user.
 */
public class Ui {
    /**
     * Prints the given String input with an indentation of 4 spaces.
     * @param str The String output that FriendlyBot wants to give the user.
     */
    public static void print(String str) {
        System.out.print("    ");
        System.out.println(str);
    }

    /**
     * Prints a welcome message for the user, when FriendlyBot is started.
     */
    public void showWelcome() {
        this.printHorizontalBar();
        Ui.print("Hello! I'm Friendly Bot");
        Ui.print("What can I do for you?");
        this.printHorizontalBar();
    }

    /**
     * Prints an exit message for the user, when the user wishes to exit FriendlyBot.
     */
    public void exitMessage() {
        Ui.print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal bar for visual separation in the console output.
     */
    public void printHorizontalBar() {
        Ui.print("____________________________________________________________");
    }
}
